package com.example.airlinereservation.exceptions;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message){
		super(message);
	}
}
