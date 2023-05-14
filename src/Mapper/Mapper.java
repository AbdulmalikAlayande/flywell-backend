package Mapper;

import data.model.Passenger;
import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;

public class Mapper {
	public static Passenger map(PassengerRequest passengerRequest) {
		Passenger newPassenger = new Passenger();
		newPassenger.setLastName(passengerRequest.getLastName());
		newPassenger.setEmail(passengerRequest.getEmail());
		newPassenger.setFirstName(passengerRequest.getFirstName());
		newPassenger.setFullName();
		newPassenger.setPhoneNumber(passengerRequest.getPhoneNumber());
		newPassenger.setPassword(passengerRequest.getPassword());
		newPassenger.setUserName(passengerRequest.getUserName());
		return newPassenger;
	}
	public static PassengerResponse map(Passenger passenger) {
		PassengerResponse response = new PassengerResponse();
		response.setEmail(passenger.getEmail());
		response.setFullName(passenger.getFullName());
		response.setPhoneNumber(passenger.getPhoneNumber());
		response.setFullName(passenger.getPassword());
		response.setUserName(passenger.getUserName());
		return response;
	}
	
	public static Passenger map(UpdateRequest updateRequest){
		Passenger updatedPassenger = new Passenger();
		return updatedPassenger;
	}
}
