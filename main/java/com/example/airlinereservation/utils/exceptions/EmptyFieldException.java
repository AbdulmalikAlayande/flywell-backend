package com.example.airlinereservation.utils.exceptions;

public class EmptyFieldException extends Exception{
	private String cause;
	
	public EmptyFieldException(String message){
		super(message);
	}
	
	public String getExceptionCause() {
		return cause;
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	public Throwable getCause(){
		return super.getCause();
	}
}
