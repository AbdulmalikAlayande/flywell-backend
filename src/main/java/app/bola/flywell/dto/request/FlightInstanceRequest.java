package app.bola.flywell.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Request DTO for {@link app.bola.flywell.data.model.flight.FlightInstance}
 */

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightInstanceRequest {

	@NotBlank
	String flightId;
	@NotNull
	LocalDateTime departureTime;
	@NotNull
	LocalDateTime arrivalTime;
	@Min(value = 1, message = "Priority is between 1 and 3, both included")
	@Max(value = 3, message = "Priority is between 1 and 3, both included")
	int priority;
}
