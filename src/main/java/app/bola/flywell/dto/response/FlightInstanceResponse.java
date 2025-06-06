package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import app.bola.flywell.data.model.flight.FlightInstance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

/**
 *  Response DTO corresponding to {@link FlightInstance}
 */
//
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightInstanceResponse extends BaseResponse {
	
	int baggageAllowance;
	long flightDuration;
	boolean isFullyBooked;
	String flightNumber;
	String flightId;
	String departureAirportName;
	String arrivalAirportName;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	AircraftResponse aircraft;
	Set<UserResponse> crew;
	Set<FlightReservationResponse> reservations;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("publicId", getPublicId())
				.add("baggageAllowance", baggageAllowance)
				.add("flightNumber", flightNumber)
				.add("flightDuration", flightDuration)
				.add("isFullyBooked", isFullyBooked)
				.add("departureAirportName", departureAirportName)
				.add("arrivalAirportName", arrivalAirportName)
				.add("departureDate", departureTime)
				.add("arrivalDate", arrivalTime)
				.toString();
	}
}
