package com.example.airlinereservation.utils.exceptions;

public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message){
		super(message);
	}
}
