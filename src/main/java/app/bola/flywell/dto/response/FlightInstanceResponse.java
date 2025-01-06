package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightInstanceResponse extends BaseResponse {
	
	int baggageAllowance;
	String flightNumber;
	long flightDuration;
	boolean isFullyBooked;
	String departureAirportName;
	String departureAirportIcaoCode;
	String departureAirportAddress;
	String arrivalAirportName;
	String arrivalAirportIcaoCode;
	String arrivalAirportAddress;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("publicId", getPublicId())
				.add("baggageAllowance", baggageAllowance)
				.add("flightNumber", flightNumber)
				.add("flightDuration", flightDuration)
				.add("isFullyBooked", isFullyBooked)
				.add("departureAirportName", departureAirportName)
				.add("departureAirportIcaoCode", departureAirportIcaoCode)
				.add("departureAirportAddress", departureAirportAddress)
				.add("arrivalAirportName", arrivalAirportName)
				.add("arrivalAirportIcaoCode", arrivalAirportIcaoCode)
				.add("arrivalAirportAddress", arrivalAirportAddress)
				.add("departureDate", departureTime)
				.add("arrivalDate", arrivalTime)
				.add("createdDate", getCreatedDate())
				.add("lastModifiedDate", getLastModifiedDate())
				.add("createdBy", getCreatedBy())
				.add("createdByRole", getCreatedByRole())
				.add("lastModifiedBy", getLastModifiedBy())
				.toString();
	}
}
