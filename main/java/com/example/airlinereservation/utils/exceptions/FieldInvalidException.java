package com.example.airlinereservation.utils.exceptions;

public class FieldInvalidException extends Exception{
	private String exceptionCause;
	private Throwable cause;
	
	public FieldInvalidException(String message) {
	}
	
	public void setCause(String cause) {
		this.exceptionCause = cause;
	}
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
	public String getExceptionCause(){
		return this.exceptionCause;
	}
	
	public Throwable getCause(){
		if (this.cause == null)
			return super.getCause();
		return this.cause;
	}
	
	public String getMessage(){
		return this.exceptionCause;
	}
}
