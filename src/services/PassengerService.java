package services;

import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;

import java.util.List;

public interface PassengerService {
	
	PassengerResponse registerNewPassenger(PassengerRequest passengerRequest);
	PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest);
	PassengerResponse findPassengerById(String passengerId);
	List<PassengerResponse> getAllPassengersBy(String flightId);
	boolean removePassengerBId(String passengerId);
	int getCountOfPassengers();
	
	PassengerResponse findPassengerByEmailAndPassword(String email, String password);
	
	PassengerResponse findPassengerByUserName(String userName);
	
	boolean removePassengerByUserName(String userName);
}
