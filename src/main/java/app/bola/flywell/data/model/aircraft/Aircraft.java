package app.bola.flywell.data.model.aircraft;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.AircraftStatus;
import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;

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

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Seat> seats = new LinkedHashSet<>();


	public void addSeat(Seat seat) {
		if (seat != null) {
			seats.add(seat);
		}
	}
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("publicId", getPublicId())
				.add("hangarId", hangarId)
				.add("capacity", capacity)
				.add("available", available)
				.add("model", model)
				.add("manufacturer", manufacturer)
				.add("locationCode", locationCode)
				.add("registrationNumber", registrationNumber)
				.add("datePurchased", datePurchased)
				.add("status", status)
				.add("position", position)
				.add("seats", seats.size())
				.toString();

	}
}


