package Mapper;

import data.model.Flight;
import dtos.Request.FlightRequest;
import dtos.Response.FlightResponse;

public class FlightMapper {
	
	public static Flight map(FlightRequest flightRequest) {
		Flight flight = new Flight();
		flight.setAirline(flightRequest.getAirline());
		flight.setArrivalDate(flightRequest.getArrivalDate());
		flight.setArrivalTime(flightRequest.getArrivalTime());
		flight.setDestination(flightRequest.getDestination());
		flight.setBaggageAllowance(flightRequest.getBaggageAllowance());
		flight.setDepartureDate(flightRequest.getDepartureDate());
		flight.setDepartureTime(flightRequest.getDepartureTime());
		return flight;
	}
	public static FlightResponse map(Flight flight) {
		FlightResponse flightResponse = new FlightResponse();
		flightResponse.setAirline(flight.getAirline());
		flightResponse.setFlightId(flight.getFlightId());
		flightResponse.setArrivalDate(flight.getArrivalDate());
		flightResponse.setArrivalTime(flight.getArrivalTime());
		flightResponse.setDestination(flight.getDestination());
		flightResponse.setBaggageAllowance(flight.getBaggageAllowance());
		flightResponse.setDepartureDate(flight.getDepartureDate());
		flightResponse.setDepartureTime(flight.getDepartureTime());
		return flightResponse;
	}
}
