package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;

import app.bola.flywell.data.model.users.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Response DTO for {@link Customer} model
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponse extends BaseResponse {

	String email;
	String firstName;
	String lastName;
	String phoneNumber;
}
