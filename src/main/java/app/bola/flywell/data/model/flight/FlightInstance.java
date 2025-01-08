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

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInstance extends FlyWellModel {

	private boolean isFullyBooked = Boolean.FALSE;

	@Column(unique = true)
	@FlightNumberSequence(name = "flight_number_sequence", startWith = 1000, incrementBy = 3)
	private String flightNumber;

	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int baggageAllowance;

	@OneToOne
	private Aircraft airCraft;

	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Flight flight;

	@Enumerated(STRING)
	private FlightStatus status;

	@OneToMany
	@ToString.Exclude
	private Set<FlightSeat> flightSeat = new LinkedHashSet<>();

	private int priority = 0;
	private long durationMinutes;

	@PostLoad
	@PostPersist
	@PostUpdate
	private void computeDuration() {
		if (departureTime != null && arrivalTime != null) {
			this.durationMinutes = Duration.between(departureTime, arrivalTime).toMinutes();
		}
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
