package utils.exceptions;

import javax.security.auth.login.CredentialNotFoundException;

public class InvalidRequestException extends CredentialNotFoundException {
	public InvalidRequestException(String message){
		super(message);
	}
}
