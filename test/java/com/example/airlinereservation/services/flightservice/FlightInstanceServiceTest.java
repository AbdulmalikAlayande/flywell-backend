package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.dtos.Request.CreateFlightInstanceRequest;
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
	
	@BeforeEach
	void startEachTestWith() {
		
	}
	
	@Test
	@SneakyThrows
	public void createNewFlightInstance_NewFlightIsCreatedTest(){
		FlightInstanceResponse response = flightInstanceService.createNewInstance(buildInstance());
		System.out.println("response is ==> "+response);
		assertThat(response).isNotNull();
	}
	
	private CreateFlightInstanceRequest buildInstance() {
		return CreateFlightInstanceRequest.builder()
				       .arrivalDate(LocalDate.of(2023, Month.DECEMBER, 23))
				       .departureDate(LocalDate.of(2023, Month.DECEMBER, 22))
				       .arrivalState("Lagos")
				       .departureState("Abuja")
				       .departureTime(LocalTime.of(9, 30, 45))
				       .build();
	}
	
	@Test
	public void createNewFlightInstance_ProperFlightSpacingIsApplied_ToMaintainSafeDistanceBetweenConsecutiveFlights(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstanceTest(){
	
	}
	
	@Test void createNewFlightInstance_AssignAircraftToFlightInstance_AircraftIsAssignedIfPassedFlightRequirement(){
	
	}
	
	
}