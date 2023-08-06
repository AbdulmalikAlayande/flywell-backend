package com.example.airlinereservation.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightInstance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@NotBlank
	private LocalDate departureDate;
	@NotBlank
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	@NotBlank
	private int baggageAllowance;
	@Transient
	private AirCraft airCraft;
	@OneToMany
	private List<FlightSeat> flightSeat;

}
