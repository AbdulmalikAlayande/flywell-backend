package data.repositories;

import data.model.Flight;
import utils.appUtils.IdLetterCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightRepositoryImplementation implements FlightRepository {
	private final List<Flight> listOfFlights = new ArrayList<>();
	@Override
	public Flight saveFlight(Flight flight) {
		if (flightDoesNotExist(flight))
			saveNewFlight(flight);
		return existingFlight(flight);
	}
	
	private Flight existingFlight(Flight flight) {
		Flight foundFlight = findFlightBy(flight.getFlightId());
		if (foundFlight != null) return flight;
		return null;
	}
	
	private boolean flightDoesNotExist(Flight flight) {
		return !listOfFlights.contains(flight) || flight.getFlightId() == null;
	}
	
	private void saveNewFlight(Flight flight) {
		flight.setFlightId(generatedId());
		listOfFlights.add(flight);
	}
	
	private String generatedId() {
		int counter = 0;
		String idRecognizer = "Flight-"+IdLetterCharacter.values().length;
		String flightId =  idRecognizer+ counter + getCountOfAllFlights() + IdLetterCharacter.getCharacter();
		if (getCountOfAllFlights()  % 10 == 0){
			counter++;
			flightId =  idRecognizer+ counter + getCountOfAllFlights() + IdLetterCharacter.getCharacter();
		}
		return flightId;
	}
	
	@Override
	public boolean deleteFlightBy(String flightId) {
		Flight foundFlight = findFlightBy(flightId);
		if (foundFlight != null) {
			listOfFlights.remove(foundFlight);
			return true;
		}
		return false;
	}
	
	@Override
	public Flight findFlightBy(String flightId) {
		for(Flight flight: listOfFlights) if (Objects.equals(flight.getFlightId(), flightId)) return flight;
		return null;
	}
	
	@Override
	public int getCountOfAllFlights() {
		return listOfFlights.size();
	}
	
	@Override
	public List<Flight> getAllFlights() {
		return listOfFlights;
	}
}
