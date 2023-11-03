package com.example.airlinereservation.data.model.aircraft;

import com.example.airlinereservation.data.model.enums.Destinations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hanger")
public class AirCraft {
	@Id
	private String id;
	@Column(unique = true, nullable = false)
	private UUID hangerId = UUID.randomUUID();
	private String airCraftName;
	private String model;
	private LocalDate datePurchased;
	@Enumerated(STRING)
	private Destinations location;
	private boolean isAvailable;
	private final int numberOfSeats = BigInteger.valueOf(20).intValue();
	@Transient
	private final Seat[] seats = new Seat[numberOfSeats];
	public final boolean[] aircraftSeats = new boolean[numberOfSeats];

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
