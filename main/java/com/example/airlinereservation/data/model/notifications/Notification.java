package com.example.airlinereservation.data.model.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public sealed abstract class Notification permits Email, TextMessage {
	private LocalDateTime createdOn;
	@JsonProperty("email")
	private String content;
	private MultipartFile file;
	private Sender mailSender;
}
