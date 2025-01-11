package app.bola.flywell.data.model.aircraft;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.flight.FlightSeat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hanger")
public class Aircraft extends FlyWellModel {

	@Column(unique = true, nullable = false)
	private String hangarId;

	private int capacity;
	private boolean available;
	private String registrationNumber;
	private String model;
	private String manufacturer;
	private String locationCode;
	private LocalDate datePurchased;

	@Enumerated(STRING)
	private AircraftStatus status;

	@OneToOne
	private Position position;

	@OneToMany
	private Set<FlightSeat> seats = new LinkedHashSet<>();


	@Override
	public boolean equals(Object object){
		if (object == null || object.getClass() != this.getClass())
			return false;
		Aircraft airCraft = (Aircraft) object;
		return airCraft.getHangarId().equals(this.getHangarId());
	}

	public int hashCode(){
		return Objects.hash(hangarId);
	}
}


