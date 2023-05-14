package services;

import data.model.Flight;
import dtos.Request.BookingRequest;
import dtos.Request.FlightRequest;
import dtos.Response.FlightResponse;

public interface Bookable {
	Flight bookFlight(BookingRequest bookingRequest);
	Flight checkAvailableFlight();
	FlightResponse saveFlight(FlightRequest flightRequest);
	void getAvailableSeatsByFlightId(String flightId);
	String cancelFlight(String passengerUsername);
}
