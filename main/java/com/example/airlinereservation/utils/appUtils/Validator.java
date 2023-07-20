package com.example.airlinereservation.utils.appUtils;

public interface Validator {
	
	void validateEmail(String email);
	void validatePassword(String password);
	void validatePhoneNumber(String phoneNumber);
	boolean fieldLengthIsValid(String field, int minimumLength, int maximumLength);
	boolean fieldLengthIsValid(String field, int minimumLength);
}
