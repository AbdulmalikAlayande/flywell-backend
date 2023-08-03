package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightSeat extends Seat{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private SeatStatus status;
	private BigDecimal seatPrice;
	private String reservationNumber;
}
