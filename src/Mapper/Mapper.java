package Mapper;

import data.model.Passenger;
import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;

public class Mapper {
	public static Passenger map(PassengerRequest passengerRequest) {
		return Passenger.builder()
				       .password(passengerRequest.getPassword())
				       .lastName(passengerRequest.getLastName())
				       .firstName(passengerRequest.getFirstName())
				       .Email(passengerRequest.getEmail())
				       .userName(passengerRequest.getUserName())
				       .phoneNumber(passengerRequest.getPhoneNumber())
				       .build();
	}
	public static PassengerResponse map(Passenger passenger) {
		return PassengerResponse.builder()
				       .id(passenger.getId())
				       .Email(passenger.getEmail())
				       .fullName(passenger.getFullName())
				       .phoneNumber(passenger.getPhoneNumber())
				       .userName(passenger.getUserName())
				       .flightId(passenger.getFlightId())
				       .build();
	}
	
	public static Passenger map(UpdateRequest updateRequest){
		Passenger updatedPassenger = new Passenger();
		return updatedPassenger;
	}
}
