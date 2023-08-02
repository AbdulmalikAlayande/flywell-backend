package com.example.airlinereservation.data.model.notifications;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public sealed abstract class Notification permits Email, TextMessage {
	
	private LocalDateTime createdOn;
	private String content;
	private MultipartFile file;
}
