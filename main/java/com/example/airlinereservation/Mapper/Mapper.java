package com.example.airlinereservation.Mapper;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.CustomerResponse;

public class Mapper {

	public static CustomerResponse map(Passenger passenger) {
		return CustomerResponse.builder()
				       .id(passenger.getId())
				       .message("Registration Successful")
				       .build();
	}
	
	public static Passenger map(UpdateRequest updateRequest){
		Passenger updatedPassenger = new Passenger();
		return updatedPassenger;
	}
}
