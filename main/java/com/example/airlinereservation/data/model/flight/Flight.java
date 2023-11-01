package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.Airport;
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
	private long flightDuration;
	@NotBlank
	private String airline;
	@Column(unique = true)
	private String flightNumber;
	@Column(name = "from_where")
	private Destinations fromWhere;
	@Enumerated(EnumType.STRING)
	@Column(name = "to_where")
	private Destinations toWhere;
	@OneToOne
	private Airport departureAirport;
	@OneToOne
	private Airport arrivalAirport;
	@OneToMany
	private List<FlightInstance> flightInstances;
}
