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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public non-sealed class Email extends Notification{
	private String id;
	private String phoneNumber;
	private Customer person;
	private LocalDateTime createdOn;
	private MultipartFile file;
	@JsonProperty("htmlContent")
	private String content;
	private Sender mailSender;
}
