package services;

import data.model.TravelClass;
import dtos.Request.BookingRequest;
import dtos.Request.PassengerRequest;
import dtos.Response.PassengerResponse;

public class myService {
	
	PassengerService passengerService = PassengerServiceImplementation.getInstance();
	PassengerRequest passengerRequest = new PassengerRequest();
	
	Bookable bookable = new FlightBooking();
	BookingRequest bookingRequest = new BookingRequest();
	PassengerService passengerService1 = PassengerServiceImplementation.getInstance();
	
	public PassengerResponse myMethod(){
		bookingRequest.setPassengerUsername("yktv");
		bookingRequest.setBookingCategory(3);
		passengerRequest.setEmail("bala@gmail.com");
		passengerRequest.setUserName("bala");
		passengerRequest.setPassword("yktv@49");
		passengerRequest.setUserName("yktv");
		passengerService.registerNewPassenger(passengerRequest);
		PassengerResponse passengerResponse = passengerService.findPassengerByUserName(bookingRequest.getPassengerUsername());
		System.out.println(passengerResponse.getUserName());
		return passengerResponse;
	}
	
	public static void main(String[] args) {
		myService service = new myService();
		System.out.println(service.myMethod());
	}
}
