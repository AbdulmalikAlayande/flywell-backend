package com.example.airlinereservation.Mapper;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;

public class FlightMapper {
	
	public static Flight map(FlightRequest flightRequest) {
		return Flight.builder()
				       .departureDate(flightRequest.getDepartureDate())
				       .arrivalDate(flightRequest.getArrivalDate())
				       .Airline(flightRequest.getAirline())
				       .arrivalTime(flightRequest.getArrivalTime())
				       .departureTime(flightRequest.getDepartureTime())
				       .destination(Destinations.valueOf(flightRequest.getDestination()))
				       .baggageAllowance(flightRequest.getBaggageAllowance())
				       .build();
	}
	public static FlightResponse map(Flight flight) {
		return FlightResponse.builder()
				       .flightId(flight.getId())
				       .Airline(flight.getAirline())
				       .arrivalDate(flight.getArrivalDate())
				       .arrivalTime(flight.getArrivalTime())
				       .baggageAllowance(flight.getBaggageAllowance())
				       .departureDate(flight.getDepartureDate())
				       .departureTime(flight.getDepartureTime())
				       .destination(String.valueOf(flight.getFrom()).toLowerCase())
				       .build(); 
	}
}
