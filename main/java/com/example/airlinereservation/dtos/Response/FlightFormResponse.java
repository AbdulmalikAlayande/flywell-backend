package com.example.airlinereservation.dtos.Response;
import com.example.airlinereservation.data.model.TravelClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.airlinereservation.data.model.Price;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FlightFormResponse {
	private Price flightPrice;
	private String passengerName;
	private String destination;
	private TravelClass travelClass;
	private int flightSeatNumber;//get the index of the seat, the seat number
	private int passengerConfirmationNumber;
	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String Airline;
	private int baggageAllowance;
}
