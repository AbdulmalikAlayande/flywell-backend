package com.example.airlinereservation.dtos.Response;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInstanceResponse {
	
	private int baggageAllowance;
	private long flightNumber;
	private long flightDuration;
	private boolean isFullyBooked;
	private String departureAirportName;
	private String departureAirportCode;
	private String departureAirportAddress;
	private String arrivalAirportName;
	private String arrivalAirportCode;
	private String arrivalAirportAddress;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalDate departureDate;
	private LocalDate arrivalDate;
	
}
