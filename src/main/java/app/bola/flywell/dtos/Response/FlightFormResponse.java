package app.bola.flywell.dtos.Response;
import app.bola.flywell.data.model.enums.Price;
import app.bola.flywell.data.model.enums.TravelClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FlightFormResponse {
	private Price flightPrice;
	private String passengerName;
	private String destination;
	private TravelClass travelClass;
	private int flightSeatNumber;//get the index of the seat, the seat number
	private int passengerConfirmationNumber;
	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String Airline;
	private int baggageAllowance;
}
