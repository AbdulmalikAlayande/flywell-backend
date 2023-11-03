package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.Airport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
	@NonNull
	private Long estimatedFlightDurationInMinutes;
	@NotBlank
	private String airline;
	@OneToOne(cascade = REMOVE)
	private Airport departureAirport;
	@OneToOne(cascade = REMOVE)
	private Airport arrivalAirport;
}
