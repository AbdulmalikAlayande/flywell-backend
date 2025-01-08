package app.bola.flywell.data.model.flight;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.Airport;
import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.LinkedHashSet;
import java.util.Set;


import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"departure_city", "arrival_city"}))
public class Flight extends FlyWellModel {

	private long duration;
	private String arrivalCity;
	private String departureCity;
	private String displayImage;

	@OneToOne(cascade = ALL)
	private Airport departureAirport;

	@OneToOne(cascade = ALL)
	private Airport arrivalAirport;

	@OneToMany(mappedBy = "flight", cascade = ALL, fetch = EAGER)
	private Set<FlightInstance> instances = new LinkedHashSet<>();


	public void addFlightInstance(FlightInstance newInstance){
		newInstance.setFlight(this);
		this.instances.add(newInstance);
	}


	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("publicId", getPublicId())
				.add("duration", duration)
				.add("arrivalCity", arrivalCity)
				.add("departureCity", departureCity)
				.add("displayImage", displayImage)
				.add("departureAirport", departureAirport)
				.add("arrivalAirport", arrivalAirport)
				.add("instances", instances)
				.toString();
	}
}


