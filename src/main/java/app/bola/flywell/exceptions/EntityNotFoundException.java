package app.bola.flywell.exceptions;

public class EntityNotFoundException extends RuntimeException{
	
	public EntityNotFoundException(){
		super();
	}

	public EntityNotFoundException(String message){
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
