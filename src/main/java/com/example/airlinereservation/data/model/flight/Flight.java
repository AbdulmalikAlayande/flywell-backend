package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.Airport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.REMOVE;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private long flightDuration;
	@NotBlank
	private String airline;
	@Column(unique = true)
	private String flightNumber;
	@OneToOne(cascade = REMOVE)
	private Airport departureAirport;
	@OneToOne(cascade = REMOVE)
	private Airport arrivalAirport;
}
