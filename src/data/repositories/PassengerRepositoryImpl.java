package data.repositories;

import data.model.Passenger;
import utils.appUtils.IdLetterCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PassengerRepositoryImpl implements PassengerRepository{
	
	
	private final List<Passenger> listOfPassengers = new ArrayList<>();
	
	@Override
	public Passenger savePassenger(Passenger passenger) {
		if(passengerDoesNotExist(passenger))
			saveNewPassenger(passenger);
		return existingPassenger(passenger);
	}
	
	private boolean passengerDoesNotExist(Passenger passenger) {
		return !listOfPassengers.contains(passenger) || passenger.getId() == null;
	}
	
	private void saveNewPassenger(Passenger passenger) {
		passenger.setId(generatedId());
		listOfPassengers.add(passenger);
	}
	
	private Passenger existingPassenger(Passenger passenger) {
		return getPassengerById(passenger.getId());
	}
	
	@Override
	public int getCountOfPassengers() {
		return listOfPassengers.size();
	}
	
	@Override
	public int getSeatNumberOfPassenger(int passengerSeat) {
		return 0;
	}
	
	@Override
	public List<Passenger> getAllPassengers() {
		return listOfPassengers;
	}
	
	@Override
	public Passenger findPassengerByUserName(String userName) {
		for (Passenger passenger : listOfPassengers)
			if (Objects.equals(passenger.getUserName(), userName)) return passenger;
		return null;
	}
	
	@Override
	public Passenger findPassengerByEmailAndPassword(String email, String password) {
		for (Passenger passenger : listOfPassengers)
			if (Objects.equals(passenger.getEmail(), email) && Objects.equals(passenger.getPassword(), password)) return passenger;
		return null;
	}
	
	@Override
	public boolean removePassengerByUserName(String userName) {
		Passenger foundPassenger = findPassengerByUserName(userName);
		if (foundPassenger != null) {
			listOfPassengers.remove(foundPassenger);
			return true;
		}
		return false;
	}
	
	@Override
	public Passenger getPassengerById(String passengerId) {
		for (Passenger passenger : listOfPassengers)
			if (Objects.equals(passenger.getId(), passengerId)) return passenger;
		return null;
	}
	
	
	@Override
	public boolean removePassenger(String passengerId) {
		Passenger foundPassenger = getPassengerById(passengerId);
		if (foundPassenger != null){
			listOfPassengers.remove(foundPassenger);
			return true;
		}
		return false;
	}
	
	private String generatedId() {
		int counter = 0;
		int idRecognizer = IdLetterCharacter.values().length;
		String userId =  idRecognizer+ counter + getCountOfPassengers() + IdLetterCharacter.getCharacter();
		if (getCountOfPassengers()  % 10 == 0){
			counter++;
			userId =  idRecognizer+ counter + getCountOfPassengers() + IdLetterCharacter.getCharacter();
		}
		return userId;
	}
	//	@Override
//	public List<Passenger> getAllPassengersBy(String flightId) {
//		List<Passenger> listOfPassengersBelongingToTheFlight = new ArrayList<>();
	//		for (Passenger passenger : listOfPassengers) if (Objects.equals(passenger.getFlightId(), flightId))
//				listOfPassengersBelongingToTheFlight.add(passenger);
//		return listOfPassengersBelongingToTheFlight;
//	}
}
