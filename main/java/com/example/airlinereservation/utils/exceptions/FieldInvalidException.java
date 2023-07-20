package com.example.airlinereservation.utils.exceptions;

public class FieldInvalidException extends RuntimeException{
	private String cause;
	
	public FieldInvalidException(String message) {
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	public String getExceptionCause(){
		return this.cause;
	}
	
	public Throwable getCause(){
		return super.getCause();
	}
}
