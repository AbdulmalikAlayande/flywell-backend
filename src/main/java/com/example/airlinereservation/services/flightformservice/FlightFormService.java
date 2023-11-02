package com.example.airlinereservation.services.flightformservice;
import com.example.airlinereservation.dtos.Request.FlightFormRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightFormResponse;

import java.util.List;
import java.util.Optional;

public interface FlightFormService {
	Optional<FlightFormResponse> generateFlightForm(FlightRequest flightRequest);
	Optional<FlightFormResponse> save(FlightFormRequest flightFormRequest);
	Optional<FlightFormResponse> findById(String flightFormId);
	String deleteFlightFormBy(String flightFormId);
	Optional<List<FlightFormResponse>> findAll();
	Optional<List<FlightFormResponse>> findAllByPassengerId(String passengerId);
	Optional<List<FlightFormResponse>> findAllByFlightId(String flightId);
	
}
