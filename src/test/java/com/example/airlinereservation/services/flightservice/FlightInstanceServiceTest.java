package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.dtos.Request.CreateFlightInstanceRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightInstanceResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FlightInstanceServiceTest {
	
	@Autowired
	private FlightInstanceService flightInstanceService;
	@Autowired
	private FlightService flightService;
	private FlightInstanceResponse response;
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
		System.out.println("response is ==> "+response);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void createNewFlightInstance_ProperFlightSpacingIsApplied_ToMaintainSafeDistanceBetweenConsecutiveFlights(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstanceTest(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstance_AircraftIsAssignedIfPassedFlightRequirement(){
	
	}
	
	private CreateFlightInstanceRequest buildInstance() {
		return CreateFlightInstanceRequest.builder()
				       .arrivalDate(LocalDate.of(2023, Month.DECEMBER, 23))
				       .departureDate(LocalDate.of(2023, Month.DECEMBER, 22))
				       .arrivalState("Rivers")
				       .departureState("Abuja")
				       .departureTime(LocalTime.of(9, 30, 45))
				       .build();
	}
	
	private FlightRequest buildFlight() {
		return FlightRequest.builder()
				       .flightDuration(3L)
				       .arrivalState("Rivers")
				       .departureState("Abuja")
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