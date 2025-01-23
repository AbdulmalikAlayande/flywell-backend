package app.bola.flywell.data.model;


import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.*;
import app.bola.flywell.data.model.flight.FlightReservation;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends FlyWellModel {

	private BigDecimal amount;
	private Currency currency;
	private PaymentStatus status;
	private LocalDateTime timeStamp;
	private PaymentMethod paymentMethod;

	@ManyToOne
	private FlightReservation reservation;
}
