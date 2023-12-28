package com.example.airlinereservation.dtos.Request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightInstanceRequest {
	
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	@NonNull
	private String arrivalCity;
	@NonNull
	private String departureCity;
	
}
