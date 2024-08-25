package app.bola.flywell.data.model;


import app.bola.flywell.data.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
	private Price price;
	private PaymentStatus status;
	private PaymentMethod paymentMethod;
	private LocalDateTime timeStamp;
}
