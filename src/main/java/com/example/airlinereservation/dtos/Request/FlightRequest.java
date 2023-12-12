package com.example.airlinereservation.dtos.Request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightRequest {
	
	@NonNull
	private Long flightNumber;
	private long estimatedFlightDurationInMinutes;
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
	private String departureCity;
	@NonNull
	private String arrivalCity;
	private Long longitudeOfAirport;
	private Long latitudeOfAirport;
}
