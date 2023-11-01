package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.math.BigInteger.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class FlightServiceTest {
	
	@Autowired
	private FlightService flightService;
	
	@BeforeEach
	public void startEachTestWith(){
		flightService.removeAll();
	}
	
	@Test
	@SneakyThrows
	public void createNewFlight_NewFlightIsAddedToAirlinesListOfFlightsTest(){
		FlightResponse savedFlightResponse = flightService.addFlight(buildFlightRequest());
		assertThat(flightService.getCountOfAllFlights()).isGreaterThan(ZERO.longValue());
		assertThat(savedFlightResponse).isNotNull();
		assertThat(savedFlightResponse.getFlightNumber()).isNotZero();
	}
	
	@Test
	@SneakyThrows
	public void createMultipleFlightsWithSameFlightNo_ExceptionIsThrown(){
		FlightResponse savedFlightResponse = flightService.addFlight(buildFlightRequest());
		assertThatThrownBy(()->flightService.addFlight(buildFlightRequest()))
											.isInstanceOf(Exception.class);
	}
	
	@Test
	public void createFlightWithEmptyRequiredFields_ExceptionIsThrown(){
		assertThatThrownBy(()->flightService.addFlight(buildIncompleteFLight())).isInstanceOf(Exception.class);
	}
	
	private FlightRequest buildIncompleteFLight() {
		return FlightRequest.builder()
				       .flightDuration(3)
				       .build();
	}
	
	//	@Test void
	private static FlightRequest buildFlightRequest() {
		return FlightRequest.builder()
				       .flightDuration(3)
				       .flightNumber(2345)
				       .arrivalAirportCode("23456")
				       .arrivalAirportName("Murtala Muhammed Airport")
				       .arrivalAirportAddress("P.0 Box 7654, Lagos, Nigeria")
				       .departureAirportCode("45632")
				       .departureAirportName("Nnamdi Azikwe International Airport")
				       .departureAirportAddress("P.O Box 5213, Abuja, Nigeria")
				       .build();
	}
}
