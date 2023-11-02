package com.example.airlinereservation.exceptions;

public class SeatsFullyBookedException extends Exception{
	
	public SeatsFullyBookedException(String message){
		super(message);
	}
}
