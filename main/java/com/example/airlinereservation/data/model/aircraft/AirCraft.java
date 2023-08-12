package com.example.airlinereservation.data.model.aircraft;

import com.example.airlinereservation.data.model.Seat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AirCraft {
	@Id
	private String id;
	private String hangerId;
	private String airCraftName;
	private String model;
	private LocalDate datePurchased;
	private boolean isAvailable;
	private final int numberOfSeats = BigInteger.valueOf(20).intValue();
	@Transient
	private final Seat[] seats = new Seat[numberOfSeats];
	public final boolean[] aircraftSeats = new boolean[numberOfSeats];
	
	public boolean getAircraftSeatsAt(int seatIndex){
		return aircraftSeats[seatIndex];
	}
	

	@Override
	public boolean equals(Object object){
		if (object == null || object.getClass() != this.getClass())
			return false;
		AirCraft airCraft = (AirCraft) object;
		return airCraft.getHangerId().equals(this.getHangerId());
	}
	
	public int hashCode(){
		return Objects.hash(hangerId);
	}
}
