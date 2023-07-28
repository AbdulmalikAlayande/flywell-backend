package com.example.airlinereservation.utils.exceptions;

public class InvalidRequestException extends Exception {
	private String message;
	
	public InvalidRequestException(String message){
		super(message);
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
}
