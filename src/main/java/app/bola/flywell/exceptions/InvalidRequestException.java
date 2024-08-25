package app.bola.flywell.exceptions;

public class InvalidRequestException extends Exception {
	private String message;
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
	
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		if (this.message == null)
			return super.getMessage();
		return this.message;
	}
	
	@Override
	public Throwable getCause() {
		return cause;
	}
}
