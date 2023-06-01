package data.repositories;

import data.model.Passenger;

import java.util.List;

public interface PassengerRepository {
	Passenger savePassenger(Passenger passenger);
	Passenger getPassengerById(String passengerId);
//	List<Passenger> getAllPassengersBy(String flightId);
	boolean removePassenger(String passengerId);
	
	int getCountOfPassengers();
	int getSeatNumberOfPassenger(int passengerSeat);
	List<Passenger> getAllPassengers();
	
	Passenger findPassengerByUserName(String userName);
	
	Passenger findPassengerByEmailAndPassword(String email, String password);
	
	boolean removePassengerByUserName(String userName);
}
