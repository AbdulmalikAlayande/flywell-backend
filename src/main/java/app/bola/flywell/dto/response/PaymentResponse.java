package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import app.bola.flywell.data.model.Payment;
import app.bola.flywell.data.model.enums.Currency;
import app.bola.flywell.data.model.enums.PaymentMethod;
import app.bola.flywell.data.model.enums.PaymentStatus;
import app.bola.flywell.data.model.flight.FlightReservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Response DTO for the {@link Payment} model
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse extends BaseResponse {

    BigDecimal amount;
    Currency currency;
    PaymentStatus status;
    LocalDateTime timeStamp;
    PaymentMethod paymentMethod;
    FlightReservationResponse reservation;
}
