package data.repositories;

import data.model.FlightForm;

import java.util.List;

public interface FlightFormRepository {
	FlightForm saveFlightForm(FlightForm flightForm);
	FlightForm findById(String flightFormId);
	boolean deleteFlightFormBy(String flightFormId);
	List<FlightForm> getAllFlightForms();
	List<FlightForm> getAllFlightFormsBelongingToAPassenger(String passengerId);
	List<FlightForm> getAllFlightFormsForAParticularFlight(String flightId);
}
