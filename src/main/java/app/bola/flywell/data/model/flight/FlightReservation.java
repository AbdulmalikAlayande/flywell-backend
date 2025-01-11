package app.bola.flywell.data.model.flight;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FlightReservation extends FlyWellModel {

	private String reservationNumber;
	private LocalDate creationDate;
	private ReservationStatus status;
	@OneToMany(fetch = FetchType.EAGER)
	private Map<Passenger, FlightForm> formMap;
	@OneToMany(fetch = FetchType.EAGER)
	private Map<Passenger, FlightSeat> seatMap;
}
