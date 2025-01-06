package app.bola.flywell.services.flightservice;

import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dtos.request.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class FlightInstanceServiceTest {

	@Autowired
	private FlightInstanceService flightInstanceService;
	@Autowired
	private FlightService flightService;
	private FlightInstanceResponse response;
	private FlightResponse flightResponse;
	static final int ZERO = BigInteger.ZERO.intValue();

	@BeforeEach
	@SneakyThrows
	void startEachTestWith() {
		flightInstanceService.removeAll();
		flightService.removeAll();
		flightResponse = flightService.createNew(buildFlight());
	}

	@Test
	@SneakyThrows
	void createNewFlightInstance_NewFlightIsCreatedTest() {
		response = flightInstanceService.createNew(buildInstance());
		assertThat(response).hasNoNullFieldsOrPropertiesExcept("createdByRole", "lastModifiedBy");
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