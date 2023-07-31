package com.example.airlinereservation.services;

import com.example.airlinereservation.data.model.*;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.services.flightservice.Bookable;
import com.example.airlinereservation.services.passengerservice.PassengerService;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Request.PassengerRequest;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookableTest {
	@Autowired
	Bookable bookable;
	BookingRequest bookingRequest;
	Payment payment;
	PassengerRequest passengerRequest;
	@Autowired
	PassengerService passengerService;
	Flight booked;
	@Autowired
	Bookable bookable1;
	
	@BeforeEach void startAllTestWith(){
		payment = new Payment();
		bookingRequest = new BookingRequest();
		passengerRequest = new PassengerRequest();
	}
	
	@Test void testThatANewFlightCanBeCreated(){
		Flight createdFlight = bookable.createNewFlight("ABUJA");
		assertThat(createdFlight).isNotNull();
	}
	
	@SneakyThrows
	@Test void testThatNewFlightIsNotCreatedIfThePreviousFlightIsNotFilledOrFullyBooked(){
		assertThrows(InvalidRequestException.class,
				()-> bookable.createNewFlight("ABUJA"), "Previous Flight Is Not Fully Booked And Is Not Ready To Depart");
	}
	
	@Test void testThatNewFlightIsCreatedIfPreviousFlightIsFullyBooked(){
		bookable1.createNewFlight("LAGOS");
		for (int index = 0; index < BigInteger.valueOf(20).intValue(); index++) {
			Passenger passenger = new Passenger();
			passenger.setLoggedIn(true);
			bookable1.assignSeatToPassenger(passenger);
		}
		Flight createdFlight = bookable1.createNewFlight("LAGOS");
		assertThat(createdFlight).isNotNull();
		assertThat(createdFlight.getDestination()).isEqualTo(Destinations.LAGOS);
	}
	
	@Test void testThatANewFlightIsNotCreatedIfThePreviousFlightHasNotDeparted(){
	
	}
	
	@Test void checkAvailableFlight_FlightWhichIsNotFullyBooked_IsTheAvailableFlightReturned() throws InvalidRequestException {
	
	}
	
	@Test void testThatIfASectionIsFullyBooked_TheSystemThrowsASeatFullyBookedException_AndTheUserIsPromptedToBookAnotherSection(){
	
	}
	
	@Test void passengerTriesToBookFlightWithAnUndefinedDestination_InvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void passengerTriesToBookFlightWithoutBeingLoggedIn_InvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void testThatPassengerHasToRegister_BeforeTheyAreEligibleToBookAFlight_ElseInvalidRequestExceptionIsThrown(){
	
	}
	
	@SneakyThrows
	@Test
	void checkAvailableFlightTest(){
		Flight availableFlight = bookable1.getAvailableFlight("flight");
		assertNotNull(availableFlight);
	}
	@SneakyThrows
	@Test void testThatPassengerCanBookFlight_AndSeatsWillBeAssignedToThePassenger(){
		passengerService.registerNewPassenger(buildPassenger());
		bookingRequest.setBookingCategory(3);
		bookingRequest.setPassengerUsername(buildPassenger().getUserName());
		booked = bookable.bookFlight(bookingRequest);
		assertTrue(booked.getAirCraft().getAircraftSeats()[15]);
	}
	
	@SneakyThrows
	@Test void testThatAFlightHasToBeFullyBookedBeforeAnother(){
		passengerService.registerNewPassenger(buildPassenger());
		passengerService.registerNewPassenger(buildPassenger1());
		
		Flight bookedFlight = bookable.bookFlight(getBookingRequest1());
		Flight bookedFlight2 = bookable.bookFlight(getBookingRequest2());
		assertTrue(bookedFlight.getAirCraft().getAircraftSeats()[10]);
		assertTrue(bookedFlight2.getAirCraft().getAircraftSeats()[15]);
		System.out.println(booked);
	}
	
	
	@Test void bookFlight_AddPassengerToFlightListOfPassengersTest(){
	
	}
	
	@Test void bookFlight_GenerateFlightFormTest(){
	
	}
	
	private PassengerRequest buildPassenger(){
		return PassengerRequest.builder()
				       .userName("abdul@20")
				       .email("alaabdulmalik03@gmail.com")
				       .phoneNumber("07036174617")
				       .firstName("Abdulmalik")
				       .lastName("Alayande")
				       .password("ayanniyi@20")
				       .build();
	}
	
	private PassengerRequest buildPassenger1(){
		return PassengerRequest.builder()
				       .userName("crayon")
				       .email("alaabdulmalik03@gmail.com")
				       .phoneNumber("07036174617")
				       .firstName("pencil")
				       .lastName("eraser")
				       .password("ayanniyi@20")
				       .build();
	}
	@NotNull
	private BookingRequest getBookingRequest2(){
		Payment payment2 = new Payment();
		payment2.setPaymentMethod(PaymentMethod.CARD);
		payment2.setPrice(Price.ECONOMY_CLASS);
		payment2.setStatus(true);
		
		BookingRequest bookingRequest2 = new BookingRequest();
		bookingRequest2.setBookingCategory(3);
		bookingRequest2.setPayment(payment2);
		bookingRequest2.setPassengerUsername(buildPassenger1().getUserName());
		return bookingRequest2;                                  
	}
	
	private BookingRequest getBookingRequest1() {
		Payment payment1 = new Payment();
		payment1.setPaymentMethod(PaymentMethod.CASH);
		payment1.setPrice(Price.BUSINESS_CLASS);
		payment1.setStatus(true);
		
		BookingRequest bookingRequest1 = new BookingRequest();
		bookingRequest1.setBookingCategory(2);
		bookingRequest1.setPayment(payment1);
		bookingRequest1.setPassengerUsername(buildPassenger().getUserName());
		return bookingRequest1;
	}
}