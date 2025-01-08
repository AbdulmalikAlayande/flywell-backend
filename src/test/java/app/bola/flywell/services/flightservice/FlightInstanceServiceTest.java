package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dto.request.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class FlightInstanceServiceTest {

	@Autowired
	private FlightSpacingService flightSpacingService;
	@Autowired
	private FlightInstanceService flightInstanceService;
	@Autowired
	private FlightService flightService;
	private FlightInstanceResponse response;
	private FlightResponse flightResponse;

	@BeforeEach
	@SneakyThrows
	void startEachTestWith() {
		flightService.removeAll();
		flightResponse = flightService.createNew(buildFlight());
	}

	@Test
	@SneakyThrows
	void createNewFlightInstance_NewFlightIsCreatedTest() {
		response = flightInstanceService.createNew(buildInstance());
		assertThat(response).hasNoNullFieldsOrPropertiesExcept("aircraft");
	}

	@Test
	void createNewFlightInstance_AvailableAircraftIsAssociatedWithFlightInstance_AndIsAutomaticallyUnavailable() {

	}

	@Test
	void createNewFlightInstance_ProperFlightSpacingIsApplied_ToMaintainSafeDistanceBetweenConsecutiveFlights() {

	}

	@Test
	void createNewFlightInstance_AssignAircraftToFlightInstanceTest() {

	}

	@Test
	void createNewFlightInstance_AssignAircraftToFlightInstance_AircraftIsAssignedIfPassedFlightRequirement() {

	}

	@Test
	void testThatIfFlightIsFilled_FlightMovementIsScheduledImmediately() {

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
			List<FlightInstance> scheduledFlights = flightSpacingService.scheduleFlights(flights, 30);

			// Assert results
			assertEquals(3, scheduledFlights.size());
			assertTrue(scheduledFlights.get(1).getDepartureTime().isAfter(scheduledFlights.get(0).getArrivalTime().plusMinutes(30)));
			assertTrue(scheduledFlights.get(2).getDepartureTime().isAfter(scheduledFlights.get(1).getArrivalTime().plusMinutes(30)));
		}

		private FlightInstance createFlightInstance(String flightNumber, LocalDateTime departure, LocalDateTime arrival, int priority) {
			FlightInstance flight = new FlightInstance();
			flight.setFlightNumber(flightNumber);
			flight.setDepartureTime(departure);
			flight.setArrivalTime(arrival);
			flight.setPriority(priority);
			return flight;
		}



	private FlightInstanceRequest buildInstance() {
		return FlightInstanceRequest.builder()
				.flightId(flightResponse.getPublicId())
				.departureTime(LocalDateTime.parse("2021-12-12T12:00:00"))
				.arrivalTime(LocalDateTime.parse("2021-12-12T15:00:00"))
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
}