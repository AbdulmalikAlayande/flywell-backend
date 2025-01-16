package app.bola.flywell.dto.response;

import app.bola.flywell.data.model.flight.FlightReservation;
import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Map;

/**
 * Response DTO for {@link FlightReservation} model
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightReservationResponse extends BaseResponse {

    String status;
    String flightId;
    String reservationNumber;
    LocalDate creationDate;
    Map<PassengerResponse, FlightSeatResponse> seatMap;
}