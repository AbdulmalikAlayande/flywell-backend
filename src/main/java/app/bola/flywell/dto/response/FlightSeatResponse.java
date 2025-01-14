package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import app.bola.flywell.data.model.enums.SeatStatus;
import app.bola.flywell.data.model.flight.FlightSeat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Response DTO for {@link FlightSeat} model.
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightSeatResponse extends BaseResponse {

    SeatStatus status;
    int seatNumber;
    BigDecimal seatPrice;
    String reservationNumber;
}