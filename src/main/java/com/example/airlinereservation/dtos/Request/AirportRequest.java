package com.example.airlinereservation.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AirportRequest {
	
	private long longitude;
	private long latitude;
	private String airportName;
}
