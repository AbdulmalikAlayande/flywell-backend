package app.bola.flywell.dto.request;

import jakarta.validation.constraints.Min;
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

	@Min(value = 200)
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
