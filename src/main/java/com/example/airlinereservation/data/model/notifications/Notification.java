package com.example.airlinereservation.data.model.notifications;

import com.example.airlinereservation.data.model.persons.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private Sender sender;
	private LocalDateTime createdOn;
	private String htmlContent;
	@OneToMany
	private List<Recipients> to;
	private String subject;
	@OneToMany
	private List<Customer> person;
}
