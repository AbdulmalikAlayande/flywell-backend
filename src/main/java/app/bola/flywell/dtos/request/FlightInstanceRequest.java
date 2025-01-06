package app.bola.flywell.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightInstanceRequest {

	@NotBlank
	String flightId;
	@NotNull
	LocalDateTime departureTime;
	@NotNull
	LocalDateTime arrivalTime;
}
