package com.example.airlinereservation.dtos.Request;


import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.data.model.Passenger;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlightFormRequest {
	private Passenger passenger;
	private Flight flight;
}
