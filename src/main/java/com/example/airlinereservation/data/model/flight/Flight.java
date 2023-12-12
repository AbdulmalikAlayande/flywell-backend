package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.Airport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;
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
	private long estimatedFlightDurationInMinutes;
	@NotBlank
	private String airline;
	private String arrivalCity;
	private String departureCity;
	@OneToOne(cascade = ALL)
	private Airport departureAirport;
	@OneToOne(cascade = ALL)
	private Airport arrivalAirport;
}
