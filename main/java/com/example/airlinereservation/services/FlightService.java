package com.example.airlinereservation.services;

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
	@AdminMethod
	Flight saveFlightForAdminUsage(FlightRequest flightRequest);
	String flightId();
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);
	boolean deleteFlightBy(String flightId) throws InvalidRequestException;
	Optional<FlightResponse> findFlightBy(String flightId) throws InvalidRequestException;
	long getCountOfAllFlights();
	Optional<List<Flight>> getAllFLights();
	void bookFLight(BookingRequest bookingRequest);
}
