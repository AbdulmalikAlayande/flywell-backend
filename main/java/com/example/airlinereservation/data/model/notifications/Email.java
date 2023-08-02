package com.example.airlinereservation.data.model.notifications;

import com.example.airlinereservation.data.model.persons.Customer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public non-sealed class Email extends Notification{
	@Id
	private String id;
	private String phoneNumber;
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer person;
}
