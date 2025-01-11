package app.bola.flywell.dto.request;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * Request DTO for the {@link Passenger} model
 **/

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassengerRequest {

    String firstname;
    String lastname;
    String passportUrl;
    String passportNumber;
    Gender gender;
    Date dateOfBirth;
}
