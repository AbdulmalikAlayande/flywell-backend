package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
import com.example.airlinereservation.data.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightInstance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private boolean isFullyBooked;
	@Column(unique = true)
	@NonNull
	private Long flightNumber;
	@NotBlank
	private LocalDateTime departureTime;
	@NotBlank
	private LocalDateTime arrivalDate;
	@NotBlank
	private int baggageAllowance;
	@OneToOne
	private AirCraft airCraft;
	@OneToOne
	private Flight flight;
	@Enumerated(STRING)
	private FlightStatus status;
	@OneToMany()
	private List<FlightSeat> flightSeat;
}
