package com.example.airlinereservation.dtos.Request;


import com.example.airlinereservation.data.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightRequest {
	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String Airline;
	private int baggageAllowance;
	private String destination;
	private Passenger[] passengers;
	private String flightId;
}
