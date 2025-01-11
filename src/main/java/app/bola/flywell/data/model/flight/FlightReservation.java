package app.bola.flywell.data.model.flight;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlightReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String reservationNumber;
	private LocalDate creationDate;
	private ReservationStatus status;
	@OneToMany(fetch = FetchType.EAGER)
	private Map<Passenger, FlightForm> formMap;
	@OneToMany(fetch = FetchType.EAGER)
	private Map<Passenger, FlightSeat> seatMap;
}
