package app.bola.flywell.dto.request;

import app.bola.flywell.data.model.Payment;
import app.bola.flywell.data.model.enums.Currency;
import app.bola.flywell.data.model.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Request DTO for the {@link Payment} model
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    String reservationId;
    BigDecimal amount;
    Currency currency;
    LocalDateTime timeStamp;
    PaymentMethod paymentMethod;
}
