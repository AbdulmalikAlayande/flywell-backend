package app.bola.flywell.data.model.aircraft;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.Destinations;
import app.bola.flywell.data.model.flight.FlightSeat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
	@Builder.Default
	private UUID hangerId = UUID.randomUUID();

	private String airCraftName;
	private String model;
	private LocalDate datePurchased;

	@OneToOne
	private Position position;

	@Enumerated(STRING)
	private Destinations location;
	private boolean available;
	private int numberOfSeats;

	@OneToMany
	private Set<FlightSeat> seats = new LinkedHashSet<>();
	public boolean[] aircraftSeats;


	@Override
	public boolean equals(Object object){
		if (object == null || object.getClass() != this.getClass())
			return false;
		Aircraft airCraft = (Aircraft) object;
		return airCraft.getHangerId().equals(this.getHangerId());
	}

	public int hashCode(){
		return Objects.hash(hangerId);
	}
}


