package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightReservationResponse extends BaseResponse {

    String status;
    String reservationNumber;
    LocalDate creationDate;
}