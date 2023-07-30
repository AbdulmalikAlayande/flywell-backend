package com.example.airlinereservation.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@NotBlank
	private LocalTime departureTime;
	private boolean isFullyBooked;
	@NotBlank
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	@NotBlank
	private String Airline;
	@NotBlank
	private int baggageAllowance;
	@OneToOne
	private AirCraft airCraft;
	@OneToMany(cascade = CascadeType.DETACH)
	private List<FlightForm> flightForm;
	@OneToMany(cascade = CascadeType.DETACH)
	private List<Passenger> passengers;
	@NotBlank
	private Destinations destination;
	private int numberOfPassengers;
	
	@Transient
	public Passenger getPassenger(int index){
		return passengers.get(index);
	}
	
}
