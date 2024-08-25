package app.bola.flywell.data.model.flight;

import app.bola.flywell.data.model.aircraft.AirCraft;
import app.bola.flywell.data.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	@NotBlank
	private LocalDate arrivalDate;
	private LocalDate departureDate;
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
