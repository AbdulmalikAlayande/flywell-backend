package app.bola.flywell.data.model.flight;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.Payment;
import app.bola.flywell.data.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

	@ManyToOne
	private FlightInstance flightInstance;

	@OneToMany
	@Builder.Default
	private List<Payment> payment = new ArrayList<>();

	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Map<Passenger, FlightForm> formMap = new LinkedHashMap<>();

	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Map<Passenger, FlightSeat> seatMap = new LinkedHashMap<>();

}
