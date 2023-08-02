package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

public class FlightReservations {

	private String reservationNumber;
	private LocalDate creationDate;
	private ReservationStatus status;
	private Map<Passenger, FlightForm> formMap;
	private Map<Passenger, Seat> seatMap;
}
