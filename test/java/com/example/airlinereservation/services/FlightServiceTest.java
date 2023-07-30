package com.example.airlinereservation.services;
import com.example.airlinereservation.services.flightservice.FlightService;
import com.example.airlinereservation.services.flightservice.FlightServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Request.FlightUpdateRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightServiceTest {
	
	private FlightService flightService;
	FlightResponse response;
	FlightRequest flightRequest;
	
	@SneakyThrows
	@BeforeEach void startAllTestWith() {
		flightService = new FlightServiceImpl();
		flightRequest = new FlightRequest();
		response = flightService.saveFlight(buildFlight());
	}
	
	@Test void saveNewFlightTest(){
		assertNotNull(response);
		assertEquals(1, flightService.getCountOfAllFlights());
	}
	
	@Test void updateSavedFlightTest(){
		FlightResponse flightResponse1 = flightService.updateFlight(buildUpdateRequest());
		System.out.println(flightResponse1);
		assertNotNull(flightResponse1);
		assertEquals(1, flightService.getCountOfAllFlights());
	}
	
	@SneakyThrows
	@Test void deleteFlightTest(){
		boolean isDeletedFlight = flightService.deleteFlightBy(flightService.flightId());
		assertTrue(isDeletedFlight);
		assertEquals(0, flightService.getCountOfAllFlights());
	}
	
	@SneakyThrows
	@Test void findFlightByIdTest(){
		Optional<FlightResponse> response1 = flightService.findFlightBy(flightService.flightId());
		assertNotNull(response1);
		assertEquals(response1.get().getFlightId(), flightService.flightId());
	}
	
	@SneakyThrows
	@Test void getAllFlightInTheDatabaseTest(){
		flightService.saveFlight(buildFlight1());
		
		Optional<List<Flight>> allFlights = flightService.getAllFLights();
		for (int i = 0; i < flightService.getAllFLights().get().size(); i++) {
			assertNotNull(flightService.getAllFLights().get().get(i));
		}
		assertNotNull(allFlights);
		assertEquals(BigInteger.TWO.intValue(), flightService.getAllFLights().get().size());
	}
	
	@Test void bookFlightTest(){
	}
	
	private FlightRequest buildFlight() {
		return FlightRequest.builder()
				       .Airline("Bola-Air")
				       .arrivalDate(LocalDate.now())
				       .arrivalTime(LocalTime.of(17, 0))
				       .departureTime(LocalTime.of(7, 30, 0))
				       .baggageAllowance(3)
				       .departureDate(LocalDate.now())
				       .destination("Abia")
				       .passengers(new Passenger[5])
				       .build();
	}
	
	private FlightRequest buildFlight1() {
		return FlightRequest.builder()
				       .Airline("Bola-Air")
				       .arrivalDate(LocalDate.now())
				       .arrivalTime(LocalTime.of(20, 0))
				       .departureTime(LocalTime.of(4, 30, 0))
				       .baggageAllowance(5)
				       .departureDate(LocalDate.now())
				       .destination("Abia")
				       .passengers(new Passenger[5])
				       .build();
	}
	private FlightUpdateRequest buildUpdateRequest() {
		return FlightUpdateRequest.builder()
				       .flightId(flightService.flightId())
				       .departureDate(LocalDate.now())
				       .departureTime(LocalTime.of(9, 30, 0))
				       .arrivalDate(LocalDate.now())
				       .arrivalTime(LocalTime.of(19, 0))
				       .build();
	}
}