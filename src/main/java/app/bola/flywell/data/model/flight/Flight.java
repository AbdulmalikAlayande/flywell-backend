package app.bola.flywell.data.model.flight;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.Airport;
import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

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
	private List<FlightInstance> flightInstances;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Flight that = (Flight) o;
		return getArrivalCity() != null && getDepartureCity() != null && arrivalCity.equalsIgnoreCase(that.arrivalCity) &&
				departureCity.equalsIgnoreCase(that.departureCity);
	}

	@Override
	public final int hashCode() {
		Class<?> clazz = this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass(): getClass();
		return Objects.hash(arrivalCity.toLowerCase(), departureCity.toLowerCase(), clazz);
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
				.add("flightInstances", flightInstances)
				.add("createdDate", getCreatedDate())
				.add("lastModifiedDate", getLastModifiedDate())
				.add("createdBy", getCreatedBy())
				.add("createdByRole", getCreatedByRole())
				.add("lastModifiedBy", getLastModifiedBy())
				.toString();
	}
}


