package com.example.airlinereservation.dtos.Request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightInstanceRequest {
	
	private LocalDateTime departureTime;
	@NonNull
	private String arrivalCity;
	@NonNull
	private String departureCity;
	private LocalDateTime departureDate;
	
}
