package com.example.airlinereservation.data.model.notifications;

import com.example.airlinereservation.data.model.persons.Customer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public non-sealed class TextMessage extends Notification{

//	@Id
	private String id;
	private String phoneNumber;
//	@ManyToOne(cascade = CascadeType.DETACH)
	private Customer person;
}
