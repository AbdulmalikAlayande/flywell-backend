package services;

import data.model.Flight;
import dtos.Request.BookingRequest;
import dtos.Request.FlightRequest;
import dtos.Request.FlightUpdateRequest;
import dtos.Response.FlightResponse;

import java.util.List;

public interface FlightService {
	
	FlightResponse saveFlight(FlightRequest flightRequest);
	Flight saveFlightForAdmin(FlightRequest flightRequest);
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);
	boolean deleteFlightBy(String flightId);
	FlightResponse findFlightBy(String flightId);
	int getCountOfAllFlights();
	List<Flight> getAllFLights();
	void bookFLight(BookingRequest bookingRequest);
}
