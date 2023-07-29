package com.example.airlinereservation.services;

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
	
	/*
	* {@return} PassengerResponse
	* @@param PassengerRequest
	* */
	PassengerResponse registerNewPassenger(PassengerRequest passengerRequest) throws FailedRegistrationException;
	PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest);
	LoginResponse login(LoginRequest loginRequest) throws LoginFailedException;
	Optional<PassengerResponse> findPassengerById(String passengerId) throws InvalidRequestException;
	void removePassengerBId(String passengerId) throws InvalidRequestException;
	long getCountOfPassengers();
	List<PassengerResponse> getAllPassengers();
	Optional<PassengerResponse> findPassengerByEmailAndPassword(String email, String password) throws InvalidRequestException;
	
	Optional<PassengerResponse> findPassengerByUserName(String userName) throws InvalidRequestException;
	boolean removePassengerByUserName(String userName);
	
	Optional<Passenger> findPassengerByUserNameForAdmin(String passengerUsername);
	
	void removeAll();
}
