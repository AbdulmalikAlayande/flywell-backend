package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.dto.response.AircraftResponse;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.services.aircrafts.AircraftService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class FlightInstanceServiceTest {

	@Autowired
	FlightSpacingService flightSpacingService;
	@Autowired
	FlightInstanceService flightInstanceService;
	@Autowired
	FlightService flightService;
	@Autowired
	AircraftService aircraftService;

	FlightInstanceResponse response;
	FlightResponse flightResponse;

	@BeforeEach
	@SneakyThrows
	void startEachTestWith() {
		flightInstanceService.removeAll();
		flightService.removeAll();
		aircraftService.removeAll();
		flightResponse = flightService.createNew(buildFlight());
		aircraftService.createNew(buildAircraftRequest("Boeing 656", 300, "GHR", "Lockheed Martin", LocalDate.of(2018, 10, 9), UUID.randomUUID().toString()));
		aircraftService.createNew(buildAircraftRequest("Lockheed CA-1206", 700, "DBX", "Lockheed Martin", LocalDate.of(2020, 6, 3), UUID.randomUUID().toString()));
		aircraftService.createNew(buildAircraftRequest("Concord CF-781", 450, "NAL", "Concord", LocalDate.of(2014, 9, 23), UUID.randomUUID().toString()));
	}

	@Test
	@SneakyThrows
	void createNewFlightInstance_NewFlightIsCreatedTest() {
		response = flightInstanceService.createNew(buildInstance(LocalDateTime.parse("2010-09-12T12:00:00"), LocalDateTime.parse("2010-09-13T14:00:00"), 1));
		assertThat(flightInstanceService.findByPublicId(response.getPublicId())).isNotNull();
		assertThat(flightInstanceService.findAll().size()).isEqualTo(BigInteger.ONE.intValue());
		assertThat(response).hasNoNullFieldsOrPropertiesExcept("aircraft");
	}

	@Test
	public void testScheduleFlights() {
		// Create test data
		List<FlightInstance> flights = List.of(
				createFlightInstance("F1", LocalDateTime.of(2025, 1, 8, 8, 0), LocalDateTime.of(2025, 1, 8, 10, 0), 1),
				createFlightInstance("F2", LocalDateTime.of(2025, 1, 8, 8, 30), LocalDateTime.of(2025, 1, 8, 9, 30), 2),
				createFlightInstance("F3", LocalDateTime.of(2025, 1, 8, 9, 45), LocalDateTime.of(2025, 1, 8, 11, 0), 3)
		);

		// Run the algorithm
		List<FlightInstance> scheduledFlights = flightSpacingService.scheduleFlights(new ArrayList<>(flights), 30);

		// Assert results
		assertEquals(2, scheduledFlights.size());
//		assertTrue(scheduledFlights.get(1).getDepartureTime().isAfter(scheduledFlights.get(0).getArrivalTime().plusMinutes(30)));
//		assertTrue(scheduledFlights.get(2).getDepartureTime().isAfter(scheduledFlights.get(1).getArrivalTime().plusMinutes(30)));
	}

	@Test
	@SneakyThrows
	void createNewFlightInstance_AvailableAircraftIsAssociatedWithFlightInstance_AndIsAutomaticallyUnavailable() {
		response = flightInstanceService.createNew(buildInstance(LocalDateTime.parse("2021-10-08T12:00:00"), LocalDateTime.parse("2021-10-09T14:00:00"), 1));

		FlightInstanceResponse instanceWithAircraft = flightInstanceService.assignAircraft(response.getPublicId());
		// Verify that the associated aircraft is automatically marked as unavailable
		AircraftResponse aircraft = flightInstanceService.getAssignedAircraft(instanceWithAircraft.getPublicId());
		assertThat(aircraft).isNotNull();
		assertThat(aircraft.isAvailable()).isFalse();
	}

	private AircraftRequest buildAircraft() {
		return AircraftRequest.builder()
				.locationCode("null")
				.model("G-345")
				.datePurchased(LocalDate.of(12, 10, 24))
				.build();
	}

	@Test
	@SneakyThrows
	void createNewFlightInstance_ProperFlightSpacingIsApplied_ToMaintainSafeDistanceBetweenConsecutiveFlights() {
		FlightInstanceRequest instance1 = buildInstance(LocalDateTime.parse("2021-11-08T12:00:00"), LocalDateTime.parse("2021-11-09T14:00:00"), 2);
		FlightInstanceRequest instance2 = buildInstance(LocalDateTime.parse("2021-11-08T12:30:00"), null, 1);
		instance2.setDepartureTime(instance1.getDepartureTime().plusMinutes(30));

		FlightInstanceResponse response1 = flightInstanceService.createNew(instance1);
		FlightInstanceResponse response2 = flightInstanceService.createNew(instance2);

		// Verify that flight spacing is applied
		long spacing = Duration.between(
				flightInstanceService.findByPublicId(response1.getPublicId()).getArrivalTime(),
				flightInstanceService.findByPublicId(response2.getPublicId()).getDepartureTime()
		).toMinutes();
		assertThat(spacing).isGreaterThanOrEqualTo(30);

		// Edge case: Test with conflicting departure times
		FlightInstanceRequest conflictingInstance = buildInstance(null, LocalDateTime.parse("2021-11-09T12:45:00"),  1);
		conflictingInstance.setDepartureTime(instance1.getDepartureTime().plusMinutes(15));
		FlightInstanceResponse conflictingResponse = flightInstanceService.createNew(conflictingInstance);

		long adjustedSpacing = Duration.between(
				instance1.getArrivalTime(),
				flightInstanceService.findByPublicId(conflictingResponse.getPublicId()).getDepartureTime()
		).toMinutes();
		assertThat(adjustedSpacing).isGreaterThanOrEqualTo(30);
	}

	@Test
	@SneakyThrows
	void createNewFlightInstance_AssignAircraftToFlightInstanceTest() {
		response = flightInstanceService.createNew(buildInstance(LocalDateTime.parse("2021-10-08T12:00:00"), LocalDateTime.parse("2021-10-09T14:00:00"), 2));
		FlightInstanceResponse instance = flightInstanceService.assignAircraft(response.getPublicId());
		// Verify that an aircraft is assigned to the flight instance
		AircraftResponse aircraft = flightInstanceService.getAssignedAircraft(instance.getPublicId());
		assertThat(aircraft).isNotNull();
	}
	@Test
	@SneakyThrows
	void createNewFlightInstance_AssignAircraftToFlightInstance_AircraftIsAssignedIfPassedFlightRequirement() {


	}

	@Test
	void testThatIfFlightIsFilled_FlightMovementIsScheduledImmediately() {

	}

	private FlightInstance createFlightInstance(String flightNumber, LocalDateTime departure, LocalDateTime arrival, int priority) {
		FlightInstance flight = new FlightInstance();
		flight.setFlightNumber(flightNumber);
		flight.setDepartureTime(departure);
		flight.setArrivalTime(arrival);
		flight.setPriority(priority);
		return flight;
	}



	private FlightInstanceRequest buildInstance(LocalDateTime departureTime, LocalDateTime arrivalTime, int priority) {
		return FlightInstanceRequest.builder()
				.flightId(flightResponse.getPublicId())
				.departureTime(departureTime)
				.arrivalTime(arrivalTime)
				.priority(priority)
				.build();
	}

	private FlightRequest buildFlight() {
		return FlightRequest.builder()
				.duration(360)
				.destinationAirport(buildAirportRequest("Oakland Airport", "U.S.A", "3456", "45678"))
				.departureAirport(buildAirportRequest("Orlando Airport", "U.S.A", "4598", "0237"))
				.arrivalCity("Rivers")
				.departureCity("Abuja")
				.build();
	}

	public AirportRequest buildAirportRequest(String name, String country, String icaoCode, String iataCode) {
		return AirportRequest.builder()
				.name(name)
				.countryName(country)
				.icaoCode(icaoCode)
				.iataCode(iataCode)
				.longitude(-34567)
				.latitude(45678)
				.build();
	}

	private AircraftRequest buildAircraftRequest(
			String model, int capacity,
			String locationCode,
			String manufacturer,
			LocalDate datePurchased,
			String registrationNumber){
		return AircraftRequest.builder()
				.model(model)
				.capacity(capacity)
				.locationCode(locationCode)
				.manufacturer(manufacturer)
				.datePurchased(datePurchased)
				.registrationNumber(registrationNumber)
				.build();
	}
}