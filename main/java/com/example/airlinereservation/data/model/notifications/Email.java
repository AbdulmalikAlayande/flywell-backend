package com.example.airlinereservation.data.model.notifications;

import com.example.airlinereservation.data.model.persons.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

//@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public non-sealed class Email extends Notification{
//	@Id
	private String id;
	private String phoneNumber;
//	@ManyToOne(cascade = CascadeType.ALL)
	private Customer person;
	private LocalDateTime createdOn;
	@JsonProperty("email")
	private String content;
	private MultipartFile file;
}
