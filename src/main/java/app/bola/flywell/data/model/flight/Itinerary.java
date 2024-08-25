package app.bola.flywell.data.model.flight;

import app.bola.flywell.data.model.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Itinerary {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Transient
	private Airport startingAirPort;
	@Transient
	private Airport finalAirPort;
	private LocalDate creationDate;
	private LocalTime creationTime;
	@OneToMany
	private List<FlightReservation> reservations;
	@OneToMany
	private List<Passenger> passengers;
}
