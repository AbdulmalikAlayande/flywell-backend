package com.example.airlinereservation.data.model.enums;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public enum Role {
	
	ADMIN,
	CREW_MEMBER("pilot", "co-pilot", "flight attendant"),
	USER;
	
	Role(String pilot, String s, String s1) {
	}
}
