package app.bola.flywell.data.model.flight;

import app.bola.flywell.annotations.FlightNumberSequence;
import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.data.model.enums.FlightStatus;
import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInstance extends FlyWellModel {

	@Builder.Default
	private boolean isFullyBooked = Boolean.FALSE;

	@Column(unique = true)
	@FlightNumberSequence(name = "flight_number_sequence", startWith = 1000, incrementBy = 3)
	private String flightNumber;

	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int baggageAllowance;
	private int priority;
	private long durationMinutes;

	@OneToOne
	private Aircraft airCraft;

	@ManyToOne(fetch = FetchType.EAGER)
	@ToString.Exclude
	private Flight flight;

	@Enumerated(STRING)
	private FlightStatus status;

	@OneToMany(cascade = ALL, fetch = EAGER)
	@Builder.Default
	private Set<FlightSeat> seats = new LinkedHashSet<>();

	@Builder.Default
	@OneToMany(cascade = ALL, fetch = EAGER)
	private Set<FlightReservation> reservations = new LinkedHashSet<>();

	@PostLoad
	@PostPersist
	@PostUpdate
	private void computeDuration() {

		if (departureTime != null && arrivalTime != null) {
			this.durationMinutes = Duration.between(departureTime, arrivalTime).toMinutes();
		}
	}

	public void addReservation(FlightReservation reservation) {

		if (reservation!= null) {
			reservation.setFlightInstance(this);
			reservations.add(reservation);
		}
	}

	public int computeTotalPassengers(){

		return reservations.stream().mapToInt(reservation -> {
			if (reservation.getSeatMap().size() == reservation.getFormMap().size())
				return reservation.getSeatMap().size();
			else return reservation.getFormMap().size();
		}).sum();
	}
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("publicId", getPublicId())
				.add("isFullyBooked", isFullyBooked)
				.add("flightNumber", flightNumber)
				.add("departureTime", departureTime)
				.add("arrivalTime", arrivalTime)
				.add("baggageAllowance", baggageAllowance)
				.add("airCraft", airCraft)
				.add("status", status)
				.toString();
	}
}

