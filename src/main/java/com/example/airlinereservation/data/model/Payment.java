package com.example.airlinereservation.data.model;


import com.example.airlinereservation.data.model.enums.PaymentMethod;
import com.example.airlinereservation.data.model.enums.Price;
import com.example.airlinereservation.data.model.enums.PaymentStatus;
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
