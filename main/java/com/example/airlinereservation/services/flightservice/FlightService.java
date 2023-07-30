package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Request.FlightUpdateRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import com.example.airlinereservation.config.mycustomannotations.AdminMethod;
import com.example.airlinereservation.dtos.Request.FlightRequest;

import java.util.List;
import java.util.Optional;

public interface FlightService {
	
	FlightResponse saveFlight(FlightRequest flightRequest) throws InvalidRequestException;
	FlightResponse bookFLight(BookingRequest bookingRequest);
	@AdminMethod
	Flight saveFlightForAdminUsage(FlightRequest flightRequest);
	String flightId();
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);
	FlightResponse getAvailableSeatsByFlightId(String flightId);
	String cancelFlight(String passengerUsername);
	
	boolean deleteFlightBy(String flightId) throws InvalidRequestException;
	Optional<FlightResponse> findFlightBy(String flightId) throws InvalidRequestException;
	long getCountOfAllFlights();
	Optional<List<Flight>> getAllFLights();
}
