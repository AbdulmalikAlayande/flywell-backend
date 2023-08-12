package com.example.airlinereservation.Mapper;

import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;

public class FlightMapper {
	
	public static Flight map(FlightRequest flightRequest) {
		return Flight.builder()
				       .Airline(flightRequest.getAirline())
				       .build();
	}
	public static FlightResponse map(Flight flight) {
		return FlightResponse.builder()
				       .flightId(flight.getId())
				       .Airline(flight.getAirline())
				       .destination(String.valueOf(flight.getFromWhere()).toLowerCase())
				       .build(); 
	}
}
