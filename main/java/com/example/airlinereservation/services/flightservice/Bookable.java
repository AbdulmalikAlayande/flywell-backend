package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;

public interface Bookable {
	Flight bookFlight(BookingRequest bookingRequest) throws InvalidRequestException;
	Flight getAvailableFlight(String destination) throws InvalidRequestException;
	FlightResponse bookFLight(BookingRequest bookingRequest);
	FlightResponse getAvailableSeatsByFlightId(String flightId);
	String cancelFlight(String passengerUsername);
}
