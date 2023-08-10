package com.example.airlinereservation.services.notifications;

import com.example.airlinereservation.utils.exceptions.FieldInvalidException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;

public interface Validator {
	
	void validateEmail(String email) throws InvalidRequestException, FieldInvalidException;
	void validatePassword(String password) throws FieldInvalidException;
	void validatePhoneNumber(String phoneNumber);
	boolean fieldLengthIsValid(String field, int minimumLength, int maximumLength);
	boolean fieldLengthIsValid(String field, int minimumLength);
}
