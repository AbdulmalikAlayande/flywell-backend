package services;

import data.model.Passenger;
import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;
import utils.exceptions.FailedRegistrationException;
import utils.exceptions.InvalidRequestException;
import utils.mycustomannotations.AdminMethod;

import java.util.List;

public interface PassengerService {
	
	PassengerResponse registerNewPassenger(PassengerRequest passengerRequest) throws FailedRegistrationException;
	PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest);
	PassengerResponse findPassengerById(String passengerId) throws InvalidRequestException;
	@AdminMethod Passenger findPassengerByIdForAdmin(String passengerId) throws InvalidRequestException;
	List<PassengerResponse> getAllPassengersBy(String flightId);
	void removePassengerBId(String passengerId) throws InvalidRequestException;
	int getCountOfPassengers();
	List<PassengerResponse> getAllPassengers();
	PassengerResponse findPassengerByEmailAndPassword(String email, String password) throws InvalidRequestException;
	
	PassengerResponse findPassengerByUserName(String userName) throws InvalidRequestException;
	Passenger findAPassengerByUserName(String userName);
	
	boolean removePassengerByUserName(String userName) throws InvalidRequestException;
	
	@AdminMethod Passenger findPassengerByUserNameForAdmin(String passengerUsername);
}
