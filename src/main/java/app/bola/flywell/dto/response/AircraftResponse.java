package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Response DTO for {@link app.bola.flywell.data.model.aircraft.Aircraft}
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AircraftResponse extends BaseResponse {

	int capacity;
	boolean available;
	String hangarId;
	String status;
	String model;
	String message;
	String manufacturer;
	String registrationNumber;
	LocalDate datePurchased;
}
