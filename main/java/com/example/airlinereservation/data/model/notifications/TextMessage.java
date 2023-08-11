package com.example.airlinereservation.data.model.notifications;

import com.example.airlinereservation.data.model.persons.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
	private LocalDateTime createdOn;
	@JsonProperty("email")
	private String content;
	private Sender mailSender;
	private MultipartFile file;
}
