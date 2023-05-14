package AirLineReservationTest.ServicesTest;

import data.model.*;
import dtos.Request.BookingRequest;
import dtos.Request.PassengerRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Bookable;
import services.FlightBooking;
import services.PassengerService;
import services.PassengerServiceImplementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookableTest {
	Bookable bookable;
	BookingRequest bookingRequest;
	Payment payment;
	PassengerRequest passengerRequest;
	PassengerService passengerService;
	Flight booked;
	
	@BeforeEach void startAllTestWith(){
		payment = new Payment();
		passengerService = PassengerServiceImplementation.getInstance();
		bookable = new FlightBooking();
		bookingRequest = new BookingRequest();
		passengerRequest = new PassengerRequest();
	}
	
	@Test void testThatAvailableFlightCanBeChecked(){
		Bookable bookable1 = new FlightBooking();
		Flight availableFlight = bookable1.checkAvailableFlight();
		assertNotNull(availableFlight);
	}
	@Test void testThatPassengerCanBookFlight(){
		passengerRequest.setUserName("bendel");
		passengerRequest.setEmail("ala@gmail.com");
		passengerRequest.setPassword("ayanniyi@20");
		passengerService.registerNewPassenger(passengerRequest);
		bookingRequest.setBookingCategory(3);
		bookingRequest.setPassengerUsername("bendel");
		booked = bookable.bookFlight(bookingRequest);
		System.out.println("The seat at index 15 is: "+booked.getSeats()[15]);
		assertTrue(booked.getSeats()[15]);
	}
	
	@Test void testThatAFlightHasToBeFullyBookedBeforeAnother(){
		BookingRequest bookingRequest1 = getBookingRequest1();
		
		PassengerRequest passengerRequest2 = new PassengerRequest();
		passengerRequest2.setLastName("Bobo");
		passengerRequest2.setFirstName("Thyme");
		passengerRequest2.setPhoneNumber("09093456787");
		passengerRequest2.setPassword("bobo@thyme");
		passengerRequest2.setUserName("dende");
		passengerRequest2.setEmail("bobo@gmail.com");
		passengerService.registerNewPassenger(passengerRequest2);
		
		
		Payment payment2 = new Payment();
		payment2.setPaymentMethod(PaymentMethod.CARD);
		payment2.setPrice(Price.ECONOMY_CLASS);
		payment2.setStatus(true);
		
		BookingRequest bookingRequest2 = new BookingRequest();
		bookingRequest2.setBookingCategory(3);
		bookingRequest2.setPayment(payment2);
		bookingRequest2.setPassengerUsername("dende");
		
		Flight bookedFlight = bookable.bookFlight(bookingRequest1);
		Flight bookedFlight2 = bookable.bookFlight(bookingRequest2);
		System.out.println("This is: "+bookedFlight.getSeats()[10]);
		System.out.println("This is: "+bookedFlight2.getSeats()[15]);
		assertTrue(bookedFlight.getSeats()[10]);
		assertTrue(bookedFlight2.getSeats()[15]);
		System.out.println(booked);
	}
	
	@NotNull
	private BookingRequest getBookingRequest1() {
		PassengerRequest passengerRequest1 = new PassengerRequest();
		passengerRequest1.setLastName("Bolabo");
		passengerRequest1.setFirstName("curry");
		passengerRequest1.setPhoneNumber("07036174617");
		passengerRequest1.setPassword("bola@curry");
		passengerRequest1.setUserName("cadet419");
		passengerRequest1.setEmail("bola@gmail.com");
		passengerService.registerNewPassenger(passengerRequest1);
		Payment payment1 = new Payment();
		payment1.setPaymentMethod(PaymentMethod.CASH);
		payment1.setPrice(Price.BUSINESS_CLASS);
		payment1.setStatus(true);
		
		BookingRequest bookingRequest1 = new BookingRequest();
		bookingRequest1.setBookingCategory(2);
		bookingRequest1.setPayment(payment1);
		bookingRequest1.setPassengerUsername("cadet419");
		return bookingRequest1;
	}
}