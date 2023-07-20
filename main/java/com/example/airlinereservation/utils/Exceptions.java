package com.example.airlinereservation.utils;

import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import com.example.airlinereservation.utils.exceptions.FieldInvalidException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;

public class Exceptions {
	
	public static RuntimeException throwInvalidRequestException(String message) throws InvalidRequestException {
		throw new InvalidRequestException(message);
	}
	
	public static void throwFailedRegistrationException(Throwable throwable) throws FailedRegistrationException {
		String message = "Registration Failed:: "+ throwable.getMessage();
		Throwable cause = throwable.getCause();
		FailedRegistrationException exception = new FailedRegistrationException(message);
		exception.initCause(cause);
		exception.setStackTrace(throwable.getStackTrace());
		throw exception;
	}
	
	public static void throwFailedRegistrationException(String message) throws FailedRegistrationException {
		throw new FailedRegistrationException(message);
	}
	
	public static void throwFieldInvalidException(String message) {
		throw new FieldInvalidException(message);
	}
}
