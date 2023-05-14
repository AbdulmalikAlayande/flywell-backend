package data.repositories;

import data.model.Flight;

import java.util.List;

public interface FlightRepository {
	
	Flight saveFlight(Flight flight);
	boolean deleteFlightBy(String flightId);
	Flight findFlightBy(String  flightId);
	
	int getCountOfAllFlights();
	
	List<Flight> getAllFlights();
}
