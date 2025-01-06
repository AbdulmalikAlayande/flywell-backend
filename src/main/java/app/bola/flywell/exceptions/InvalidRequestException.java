package app.bola.flywell.exceptions;

import lombok.Setter;

@Setter
public class InvalidRequestException extends Exception {
	private final String message;
	private Throwable cause;
	
	public InvalidRequestException(String message){
		super(message);
		this.message = message;
	}
	
	public InvalidRequestException( String message, Throwable cause){
		super(message, cause);
		this.cause = cause;
		this.message = message;
	}

	@Override
    public String getMessage(){
		if (this.message == null)
			return super.getMessage();
		return this.message;
	}
	
	@Override
	public synchronized Throwable getCause() {
		return cause;
	}
}
