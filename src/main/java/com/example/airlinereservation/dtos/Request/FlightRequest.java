package com.example.airlinereservation.dtos.Request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightRequest {
	
	@NotBlank
	private long estimatedFlightDurationInMinutes;
	@NonNull
	@NotBlank
	private String arrivalCity;
	@NonNull
	@NotBlank
	private String departureCity;
	@NonNull
	private AirportRequest arrivalAirportRequest;
	@NonNull
	@NotBlank
	private AirportRequest departureAirportRequest;
}
