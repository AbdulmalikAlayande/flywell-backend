package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.dtos.Request.FlightUpdateRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import com.example.airlinereservation.dtos.Request.FlightRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface FlightService {
	
	FlightResponse addFlight(FlightRequest flightRequest) throws InvalidRequestException;
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);
	List<Flight> getAllFLights();
	FlightResponse getFlightByArrivalAndDepartureLocation(Destinations arrivalState, Destinations departureState) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidRequestException;
	Long getCountOfAllFlights();
	
	void removeAll();
}
