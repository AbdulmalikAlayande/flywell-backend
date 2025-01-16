package app.bola.flywell.dto.request;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Objects;

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
    LocalDate dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerRequest that = (PassengerRequest) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, passportNumber);
    }

}
