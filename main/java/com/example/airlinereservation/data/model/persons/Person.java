package com.example.airlinereservation.data.model.persons;

import com.example.airlinereservation.data.model.UserBioData;
import com.example.airlinereservation.data.model.notifications.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class Person {
	private UserBioData bioData;
}
