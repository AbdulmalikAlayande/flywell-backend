package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.enums.FlightStatus;
import com.example.airlinereservation.dtos.Request.AirportRequest;
import com.example.airlinereservation.dtos.Request.CreateFlightInstanceRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightInstanceResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class FlightInstanceServiceTest {
	
	@Autowired
	private FlightInstanceService flightInstanceService;
	@Autowired
	private FlightService flightService;
	private FlightInstanceResponse response;
	private static final int ZERO = BigInteger.ZERO.intValue();
	
	@BeforeEach
	@SneakyThrows
	void startEachTestWith() {
		flightInstanceService.removeAll();
		flightService.removeAll();
		flightService.addFlight(buildFlight());
		response = flightInstanceService.createNewInstance(buildInstance());
		
	}
	
	@Test
	@SneakyThrows
	public void createNewFlightInstance_NewFlightIsCreatedTest(){
		assertThat(response).isNotNull();
		assertThat(flightInstanceService.findAllBy(FlightStatus.SCHEDULED).size()).isGreaterThan(ZERO);
		assertThat(response.getArrivalAirportIcaoCode()).isNotNull();
		assertThat(response.getDepartureAirportIcaoCode()).isNotNull();
		assertThat(response.getArrivalDate()).isNotNull();
		assertThat(response.getDepartureDate()).isNotNull();
	}
	
	@Test
	@SneakyThrows
	public void createNewFlightInstance_ProperFlightSpacingIsApplied_ToMaintainSafeDistanceBetweenConsecutiveFlights(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstanceTest(){
	
	}
	@Test void createNewFlightInstance_AssignAircraftToFlightInstance_AircraftIsAssignedIfPassedFlightRequirement(){
	
	}
	
	@Test public void testThatIfFlightIsFilled_FlightMovementIsScheduledImmediately(){
	
	}
	
	private CreateFlightInstanceRequest buildInstance() {
		return CreateFlightInstanceRequest.builder()
				       .arrivalCity("Rivers")
				       .departureCity("Abuja")
				       .build();
	}
	
	private FlightRequest buildFlight() {
		return FlightRequest.builder()
				       .estimatedFlightDurationInMinutes(360)
				       .arrivalAirportRequest(buildAirportRequest("Oakland Airport", "U.S.A", "3456", "45678"))
				       .departureAirportRequest(buildAirportRequest("Orlando Airport", "U.S.A", "4598", "0237"))
				       .arrivalCity("Rivers")
				       .departureCity("Abuja")
				       .build();
	}
	
	public AirportRequest buildAirportRequest(String name, String country, String icaoCode, String iataCode){
		return AirportRequest.builder()
				       .airportName(name)
				       .countryName(country)
				       .icaoCode(icaoCode)
				       .iataCode(iataCode)
				       .longitude(-34567)
				       .latitude(45678)
				       .build();
	}
}