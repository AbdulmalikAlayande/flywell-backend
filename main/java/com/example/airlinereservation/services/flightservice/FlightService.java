package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.dtos.Request.FlightUpdateRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import com.example.airlinereservation.dtos.Request.FlightRequest;

import java.util.List;

public interface FlightService {
	
	FlightResponse addFlight(FlightRequest flightRequest) throws InvalidRequestException;
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);
	long getCountOfAllFlights();
	List<Flight> getAllFLights();
	
	void removeAll();
}
