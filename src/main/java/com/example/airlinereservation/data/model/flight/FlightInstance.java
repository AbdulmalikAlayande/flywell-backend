package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
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
	private boolean isFullyBooked;
	@NotBlank
	private LocalDate departureDate;
	@NotBlank
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	@NotBlank
	private int baggageAllowance;
	@OneToOne
	private AirCraft airCraft;
	@OneToOne
	private Flight flight;
	@OneToMany()
	private List<FlightSeat> flightSeat;
}
