package com.example.airlinereservation.dtos.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightRequest {
	
	@NonNull
	private Long flightNumber;
	@NonNull
	private Long flightDuration;
	@NonNull
	private String arrivalAirportName;
	@NonNull
	private String arrivalAirportCode;
	@NonNull
	private String arrivalAirportAddress;
	@NonNull
	private String departureAirportName;
	@NonNull
	private String departureAirportCode;
	@NonNull
	private String departureAirportAddress;
	@NonNull
	private String departureState;
	@NonNull
	private String arrivalState;
}

/*	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String Airline;
	private int baggageAllowance;
	private String destination;*/
