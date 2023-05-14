package data.repositories;

import data.model.FlightForm;
import utils.appUtils.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightFormRepositoryImplementation implements FlightFormRepository {
	List<FlightForm> flightForms = new ArrayList<>();
	
	@Override
	public FlightForm saveFlightForm(FlightForm flightForm) {
		if (flightFormIsNotSaved(flightForm))
			saveNewFlightForm(flightForm);
		return existingFlightForm(flightForm);
	}
	
	private boolean flightFormIsNotSaved(FlightForm flightForm) {
		return !flightForms.contains(flightForm) || flightForm.getId() == null;
	}
	
	private void saveNewFlightForm(FlightForm flightForm) {
		flightForm.setId(IdGenerator.generateIdForFlightForm());
		flightForms.add(flightForm);
	}
	
	private FlightForm existingFlightForm(FlightForm flightForm) {
		if (findById(flightForm.getId()) != null)
			return flightForm;
		return null;
	}
	
	@Override
	public FlightForm findById(String flightFormId) {
		for (FlightForm flightForm : flightForms)
			if (Objects.equals(flightForm.getId(), flightFormId)) return flightForm;
		return null;
	}
	
	@Override
	public boolean deleteFlightFormBy(String flightFormId) {
		FlightForm foundFlightForm = findById(flightFormId);
		if (foundFlightForm != null) {
			flightForms.remove(foundFlightForm);
			return true;
		}
		return false;
	}
	
	@Override
	public List<FlightForm> getAllFlightForms() {
		return flightForms;
	}
	
	@Override
	public List<FlightForm> getAllFlightFormsBelongingToAPassenger(String passengerId) {
		List<FlightForm> listOfFlightForms = new ArrayList<>();
		for (FlightForm flightForm : flightForms) {
			if (Objects.equals(flightForm.getPassengerId(), passengerId)) listOfFlightForms.add(flightForm);
			return listOfFlightForms;
		}
		return null;
	}
	
	@Override
	public List<FlightForm> getAllFlightFormsForAParticularFlight(String flightId) {
		List<FlightForm> listOfFlightForms = new ArrayList<>();
		for (FlightForm flightForm : flightForms) {
			if (Objects.equals(flightForm.getFlightId(), flightId)) listOfFlightForms.add(flightForm);
			return listOfFlightForms;
		}
		return null;
	}
}
