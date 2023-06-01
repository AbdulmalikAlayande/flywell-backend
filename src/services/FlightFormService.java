package services;

import dtos.Request.FlightFormRequest;
import dtos.Request.FlightRequest;
import dtos.Response.FlightFormResponse;

import java.util.List;

public interface FlightFormService {
	FlightFormResponse generateFlightForm(FlightRequest flightRequest);
	FlightFormResponse saveFlightForm(FlightFormRequest flightFormRequest);
	FlightFormResponse findById(String flightFormId);
	String deleteFlightFormBy(String flightFormId);
	List<FlightFormResponse> getAllFlightForms();
	List<FlightFormResponse> getAllFlightFormsBelongingToAPassenger(String passengerId);
	List<FlightFormResponse> getAllFlightFormsForAParticularFlight(String flightId);
	
}
