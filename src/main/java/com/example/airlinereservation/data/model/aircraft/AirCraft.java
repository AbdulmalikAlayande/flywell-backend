package com.example.airlinereservation.data.model.aircraft;

import com.example.airlinereservation.data.model.enums.Destinations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@OneToOne
	private Position position;
	@Enumerated(STRING)
	private Destinations location;
	private boolean isAvailable;
	private int numberOfSeats;
	@Transient
	private Seat[] seats;
	public boolean[] aircraftSeats;
	
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


