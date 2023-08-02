package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.enums.Destinations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
	private boolean isFullyBooked;
	@NotBlank
	private String Airline;
	private Destinations from;
	private Destinations to;
	private int numberOfPassengers;
	@Transient
	private Airport departureAirport;
	@Transient
	private Airport arrivalAirport;
	@OneToMany
	private List<FlightInstance> flightInstances;
}
