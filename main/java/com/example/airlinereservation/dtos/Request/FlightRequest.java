package com.example.airlinereservation.dtos.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightRequest {
	
	@NotNull
	private long flightNumber;
	@NotNull
	private long flightDuration;
	@NotNull
	private String arrivalAirportName;
	@NotNull
	private String arrivalAirportCode;
	@NotNull
	private String arrivalAirportAddress;
	@NotNull
	private String departureAirportName;
	@NotNull
	private String departureAirportCode;
	@NotNull
	private String departureAirportAddress;
}

/*	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String Airline;
	private int baggageAllowance;
	private String destination;*/
