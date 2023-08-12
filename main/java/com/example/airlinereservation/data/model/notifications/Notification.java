package com.example.airlinereservation.data.model.notifications;

import com.example.airlinereservation.data.model.persons.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@OneToOne
	private Sender sender;
	private LocalDateTime createdOn;
	@JsonProperty("htmlContent")
	private String htmlContent;
	@JsonProperty("to")
	@OneToMany
	private List<Recipients> to;
	private String subject;
	@OneToMany
	private List<NotificationMultipartFile> file;
	@OneToMany
	private List<Customer> person;
}
