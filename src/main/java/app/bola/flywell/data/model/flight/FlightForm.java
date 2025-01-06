package app.bola.flywell.data.model.flight;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.Price;
import app.bola.flywell.data.model.enums.TravelClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FlightForm {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@OneToOne(cascade = CascadeType.ALL)
	private Passenger passenger;
	@Enumerated(value = EnumType.STRING)
	private Price flightPrice;
	@NotBlank
	private String passengerName;
	@NotBlank
	private String destination;
	@Enumerated(value = EnumType.STRING)
	private TravelClass travelClass;
	@NotBlank
	private int flightSeatNumber;
	private int passengerConfirmationNumber;
	@NotBlank
	private LocalTime departureTime;
	@NotBlank
	private LocalDate departureDate;
	@NotBlank
	private LocalTime arrivalTime;
	@NotBlank
	private LocalDate arrivalDate;
	@NotBlank
	private String Airline;
	@NotBlank
	private long baggageAllowance;
}
