package app.bola.flywell.exceptions;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message){
		super(message);
	}
}
