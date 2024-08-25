package app.bola.flywell.services.notifications;

import app.bola.flywell.exceptions.*;

public interface Validator {
	
	void validateEmail(String email) throws InvalidRequestException, FieldInvalidException;
	void validatePassword(String password) throws FieldInvalidException;
	void validatePhoneNumber(String phoneNumber);
	boolean fieldLengthIsValid(String field, int minimumLength, int maximumLength);
	boolean fieldLengthIsValid(String field, int minimumLength);
}
