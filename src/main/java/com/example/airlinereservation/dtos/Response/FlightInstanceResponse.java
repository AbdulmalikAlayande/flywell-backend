package com.example.airlinereservation.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInstanceResponse {
	
	private int baggageAllowance;
	private Long flightNumber;
	private long flightDuration;
	private boolean isFullyBooked;
	private String departureAirportName;
	private String departureAirportIcaoCode;
	private String departureAirportAddress;
	private String arrivalAirportName;
	private String arrivalAirportIcaoCode;
	private String arrivalAirportAddress;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	
}
