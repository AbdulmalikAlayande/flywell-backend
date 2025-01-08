package app.bola.flywell.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightUpdateRequest {

	String flightId;
	String displayImageName;
	LocalTime departureTime;
	LocalDate departureDate;
	LocalTime arrivalTime;
	LocalDate arrivalDate;
	int baggageAllowance;
	String destination;

}
