package com.example.airlinereservation.data.model.flight;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlightReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String reservationNumber;
	private LocalDate creationDate;
	private ReservationStatus status;
	@OneToMany
	private Map<Passenger, FlightForm> formMap;
	@OneToMany
	private Map<Passenger, FlightSeat> seatMap;
}
