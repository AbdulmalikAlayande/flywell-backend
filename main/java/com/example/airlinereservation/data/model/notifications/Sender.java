package com.example.airlinereservation.data.model.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Sender {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
}
