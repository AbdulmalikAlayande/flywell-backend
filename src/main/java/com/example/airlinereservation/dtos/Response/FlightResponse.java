package com.example.airlinereservation.dtos.Response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
	private String message;
	private long flightNumber;
	private long flightDuration;
	private String arrivalAirportName;
	private String arrivalAirportCode;
	private String arrivalAirportAddress;
	private String departureAirportName;
	private String departureAirportCode;
	private String departureAirportAddress;
}
/*
 	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String Airline;
	private int baggageAllowance;
	private String destination;
*/
