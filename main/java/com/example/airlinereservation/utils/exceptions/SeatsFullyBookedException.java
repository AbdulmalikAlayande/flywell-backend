package com.example.airlinereservation.utils.exceptions;

public class SeatsFullyBookedException extends Exception{
	
	public SeatsFullyBookedException(String message){
		super(message);
	}
}
