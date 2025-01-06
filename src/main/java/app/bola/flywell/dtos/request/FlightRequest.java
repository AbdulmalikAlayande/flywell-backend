package app.bola.flywell.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;


import static app.bola.flywell.utils.Constants.EMPTY_FIELD_MESSAGE;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightRequest {

	private String displayImageName;
	private long duration;
	@NotBlank(message = EMPTY_FIELD_MESSAGE)
//	@NonNull
	private String arrivalCity;
	@NotBlank(message = EMPTY_FIELD_MESSAGE)
//	@NonNull
	private String departureCity;
//	@NonNull
	@NotNull
	private AirportRequest destinationAirport;
//	@NonNull
	@NotNull
	private AirportRequest departureAirport;
	
}
