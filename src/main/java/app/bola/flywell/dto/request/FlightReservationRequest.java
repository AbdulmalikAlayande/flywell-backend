package app.bola.flywell.dto.request;

import app.bola.flywell.data.model.flight.FlightReservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * Request DTO for the {@link FlightReservation} model
 **/
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightReservationRequest {

    String flightId;
    List<String> seatIds;
    List<PassengerRequest> passengers;
    Map<Integer, PassengerRequest> seatMap;
}