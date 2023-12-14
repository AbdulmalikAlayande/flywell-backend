package com.example.airlinereservation.data.model.notifications;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipients {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String email;
}
