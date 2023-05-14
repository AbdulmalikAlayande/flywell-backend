package AirLineReservationTest.ServicesTest;

import dtos.Request.FlightRequest;
import dtos.Request.FlightUpdateRequest;
import dtos.Response.FlightResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.FlightService;
import services.FlightServiceImpl;

import java.time.LocalTime;
import utils.DateTime.Date;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
	
	private FlightService flightService;
	private FlightUpdateRequest flightUpdateRequest;
	private FlightResponse flightResponse;
	FlightResponse response;
	FlightRequest flightRequest;
	
	@BeforeEach void startAllTestWith() {
		Date arrivalDate = new Date();
		Date departureDate = new Date();
		arrivalDate.setDate(8, 5, 2023);
		departureDate.setDate(8, 5, 2023);
		flightService = new FlightServiceImpl();
		flightResponse = new FlightResponse();
		flightRequest = new FlightRequest();
		flightRequest.setAirline("Bola-Air");
		flightRequest.setArrivalDate(arrivalDate);
		flightRequest.setDepartureDate(departureDate);
		flightRequest.setArrivalTime(LocalTime.of(17, 0));
		flightRequest.setDepartureTime(LocalTime.of(7, 30, 0));
		flightRequest.setDestination("Abia");
		flightRequest.setBaggageAllowance(3);
		flightUpdateRequest = new FlightUpdateRequest();
		response = flightService.saveFlight(flightRequest);
	}
	
	@Test void saveNewFlightTest(){
		assertNotNull(response);
		assertEquals(1, flightService.getCountOfAllFlights());
	}
	
	@Test void updateSavedFlightTest(){
		Date arrivalDate = new Date();
		Date departureDate = new Date();
		arrivalDate.setDate(11, 5, 2023);
		departureDate.setDate(10, 5, 2023);
		flightUpdateRequest.setFlightId(response.getFlightId());
		flightUpdateRequest.setArrivalTime(LocalTime.of(19, 0));
		flightUpdateRequest.setDepartureTime(LocalTime.of(9, 30, 0));
		flightUpdateRequest.setArrivalDate(arrivalDate);
		flightUpdateRequest.setDepartureDate(departureDate);
		FlightResponse flightResponse1 = flightService.updateFlight(flightUpdateRequest);
		System.out.println(flightResponse1);
		assertNotNull(flightResponse1);
		assertEquals(1, flightService.getCountOfAllFlights());
	}
	
	@Test void deleteFlightTest(){
		boolean isDeletedFlight = flightService.deleteFlightBy(flightRequest.getFlightId());
		assertTrue(isDeletedFlight);
		assertEquals(0, flightService.getCountOfAllFlights());
	}
	
	@Test void findFlightByIdTest(){
		FlightResponse response1 = flightService.findFlightBy(flightRequest.getFlightId());
		assertNotNull(response1);
		assertEquals(response1.getFlightId(), flightRequest.getFlightId());
	}
}