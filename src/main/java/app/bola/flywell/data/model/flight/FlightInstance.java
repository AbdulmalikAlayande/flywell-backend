package app.bola.flywell.data.model.flight;

import app.bola.flywell.annotations.FlightNumberSequence;
import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.aircraft.AirCraft;
import app.bola.flywell.data.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInstance extends FlyWellModel {

	private boolean isFullyBooked;
	@Column(unique = true)
	@FlightNumberSequence(name = "flight_number_sequence", startWith = 1000, incrementBy = 3)
	private String flightNumber;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int baggageAllowance;
	@OneToOne
	private AirCraft airCraft;
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Flight flight;
	@Enumerated(STRING)
	private FlightStatus status;
	@OneToMany()
	@ToString.Exclude
	private List<FlightSeat> flightSeat;

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy
				? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
				: o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy
				? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
				: this.getClass();
		if (thisEffectiveClass != oEffectiveClass)
			return false;
		FlightInstance that = (FlightInstance) o;
		return this.getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy hibernateProxy
				? hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
				: getClass().hashCode();
	}

}
