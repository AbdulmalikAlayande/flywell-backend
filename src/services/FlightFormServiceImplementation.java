package services;

import dtos.Request.FlightFormRequest;
import dtos.Request.FlightRequest;
import dtos.Response.FlightFormResponse;

import java.util.List;

public class FlightFormServiceImplementation implements FlightFormService{
	
	private static FlightFormService instance = null;
	
	public static FlightFormService getInstance() {
		if (instance == null)
			return new FlightFormServiceImplementation();
		return instance;
	}
	
	private FlightFormServiceImplementation(){}
	@Override
	public FlightFormResponse generateFlightForm(FlightRequest flightRequest) {
		return null;
	}
	
	@Override
	public FlightFormResponse saveFlightForm(FlightFormRequest flightFormRequest) {
		return null;
	}
	
	@Override
	public FlightFormResponse findById(String flightFormId) {
		return null;
	}
	
	@Override
	public String deleteFlightFormBy(String flightFormId) {
		return null;
	}
	
	@Override
	public List<FlightFormResponse> getAllFlightForms() {
		return null;
	}
	
	@Override
	public List<FlightFormResponse> getAllFlightFormsBelongingToAPassenger(String passengerId) {
		return null;
	}
	
	@Override
	public List<FlightFormResponse> getAllFlightFormsForAParticularFlight(String flightId) {
		return null;
	}
}
