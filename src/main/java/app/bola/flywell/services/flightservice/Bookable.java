package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.exceptions.InvalidRequestException;
import app.bola.flywell.dtos.Request.BookingRequest;
import app.bola.flywell.dtos.Response.FlightResponse;

public interface Bookable {
	Flight bookFlight(BookingRequest bookingRequest) throws InvalidRequestException;
	Flight getAvailableFlight(String destination) throws InvalidRequestException;
	boolean isNotFilled(Flight foundFlight);
	
	Flight createNewFlight(String abuja);
	void assignSeatToPassenger(Passenger passenger, String destination, int category);
	FlightResponse checkAvailableFlight(String destination) throws InvalidRequestException;
	FlightResponse bookFLight(BookingRequest bookingRequest);
	FlightResponse getAvailableSeatsByFlightId(String flightId);
	
	String cancelFlight(String passengerUsername);
}
