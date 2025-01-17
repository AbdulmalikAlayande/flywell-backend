package app.bola.flywell.data.model.flight;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.aircraft.Seat;
import app.bola.flywell.data.model.enums.SeatClass;
import app.bola.flywell.data.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightSeat extends FlyWellModel {

	private BigDecimal seatPrice;
	private String reservationNumber;

	@Enumerated(value = EnumType.STRING)
	private SeatStatus seatStatus;

	@OneToOne
	private Seat seatReference;


	public void setSeatPrice(SeatClass seatClass) {
		this.seatPrice = calculateSeatPrice(seatClass);
	}

	private BigDecimal calculateSeatPrice(SeatClass seatClass) {
        return switch (seatClass) {
			case FIRST_CLASS -> BigDecimal.valueOf(700.00);
			case BUSINESS -> BigDecimal.valueOf(500.00);
			case ECONOMY -> BigDecimal.valueOf(200.00);
        };
	}


	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		FlightSeat seat = (FlightSeat) o;
		return getId() != null && Objects.equals(getId(), seat.getId())
				&& getPublicId() != null && Objects.equals(getPublicId(), seat.getPublicId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
