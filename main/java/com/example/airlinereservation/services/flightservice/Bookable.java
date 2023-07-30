package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;

public interface Bookable {
	Flight bookFlight(BookingRequest bookingRequest) throws InvalidRequestException;
	Flight checkAvailableFlight() throws InvalidRequestException;
	FlightResponse saveFlight(FlightRequest flightRequest) throws InvalidRequestException;
	void getAvailableSeatsByFlightId(String flightId);
	String cancelFlight(String passengerUsername);
}
