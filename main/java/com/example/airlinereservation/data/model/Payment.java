package com.example.airlinereservation.data.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
	private Price price;
	private boolean status;
	private PaymentMethod paymentMethod;
}
