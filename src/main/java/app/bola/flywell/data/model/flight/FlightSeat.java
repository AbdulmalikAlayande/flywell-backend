package app.bola.flywell.data.model.flight;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightSeat extends FlyWellModel {

	public int seatNumber;
	private SeatStatus status;
	private BigDecimal seatPrice;
	private String reservationNumber;
}
