package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.enums.FlightStatus;
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

@SpringBootTest
class FlightInstanceServiceTest {
	
	@Autowired
	private FlightInstanceService flightInstanceService;
	@Autowired
	private FlightService flightService;
	private FlightInstanceResponse response;
	private static final int ONE = BigInteger.ONE.intValue();
	
	@BeforeEach
	@SneakyThrows
	void startEachTestWith() {
		flightService.removeAll();
		flightService.addFlight(buildFlight());
		response = flightInstanceService.createNewInstance(buildInstance());
		
	}
	
	@Test
	@SneakyThrows
	public void createNewFlightInstance_NewFlightIsCreatedTest(){
		assertThat(response).isNotNull();
		assertThat(flightInstanceService.findAllBy(FlightStatus.SCHEDULED).size()).isGreaterThan(ONE);
		assertThat(response.getArrivalAirportIcaoCode()).isNotNull();
		assertThat(response.getDepartureAirportIcaoCode()).isNotNull();
		assertThat(response.getArrivalDate()).isNotNull();
		assertThat(response.getDepartureDate()).isNotNull();
	}
	
	@Test public void testThatIfFlightIsFilled_FlightMovementIsScheduledImmediately(){
	
	}
	
	@Test
	@SneakyThrows
	public void createNewFlightInstance_ProperFlightSpacingIsApplied_ToMaintainSafeDistanceBetweenConsecutiveFlights(){
		CreateFlightInstanceRequest instance = buildInstance();
		FlightInstanceResponse response2 = flightInstanceService.createNewInstance(instance);
		
	}
	
	@Test
	public void testThatAllFlightHaveAnIntervalOfFiveMinutesBetweenEachOther(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstanceTest(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstance_AircraftIsAssignedIfPassedFlightRequirement(){
	
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
				       .arrivalCity("Rivers")
				       .departureCity("Abuja")
				       .flightNumber(2345L)
				       .arrivalAirportCode("23456")
				       .arrivalAirportName("Port Harcourt International Airport")
				       .arrivalAirportAddress("P.0 Box 7654, Lagos, Nigeria")
				       .departureAirportCode("45632")
				       .departureAirportName("Nnamdi Azikwe International Airport")
				       .departureAirportAddress("P.O Box 5213, Abuja, Nigeria")
				       .build();
	}
}