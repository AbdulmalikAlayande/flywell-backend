package com.example.airlinereservation.services.passengerservice;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.dtos.Request.LoginRequest;
import com.example.airlinereservation.dtos.Request.PassengerRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.LoginResponse;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import com.example.airlinereservation.utils.exceptions.LoginFailedException;

import java.util.List;
import java.util.Optional;
public interface PassengerService {
	
	
	PassengerResponse registerNewCustomer(PassengerRequest passengerRequest) throws FailedRegistrationException;
	PassengerResponse updateDetailsOfRegisteredCustomer(UpdateRequest updateRequest);
	LoginResponse login(LoginRequest loginRequest) throws LoginFailedException;
	Optional<PassengerResponse> findCustomerById(String passengerId) throws InvalidRequestException;
	void removeCustomerById(String passengerId) throws InvalidRequestException;
	long getCountOfPassengers();
	List<PassengerResponse> getAllPassengers();
	Optional<PassengerResponse> findPassengerByEmailAndPassword(String email, String password) throws InvalidRequestException;
	
	Optional<PassengerResponse> findPassengerByUserName(String userName) throws InvalidRequestException;
	boolean removeCustomerByUserName(String userName) throws InvalidRequestException;
	
	Optional<Passenger> findPassengerByUserNameForAdmin(String passengerUsername);
	
	void removeAll();
}
