package app.bola.flywell.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

/**
 * Request DTO for {@link app.bola.flywell.data.model.aircraft.Aircraft}
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AircraftRequest {

	int capacity;
	@NotBlank
	String model;
	@NotNull
	LocalDate datePurchased;
	String registrationNumber;
	@NotBlank
	String manufacturer;
	@NotBlank
	String locationCode;
}
