package com.example.airlinereservation.data.model.persons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OTP {
	
	@Id
	@GeneratedValue(strategy = UUID)
	private String id;
	private long data;
	private String secretKey;
	private long staleTime;
	private boolean isExpired;
	private boolean isUsed;
	
}
