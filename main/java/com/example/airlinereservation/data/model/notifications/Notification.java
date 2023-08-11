package com.example.airlinereservation.data.model.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public sealed abstract class Notification permits Email, TextMessage {
	private Sender sender;
	private LocalDateTime createdOn;
	@JsonProperty("htmlContent")
	private String htmlContent;
	@JsonProperty("to")
	private List<Recipients> recipients;
	@JsonProperty("cc")
	private List<String> carbonCopyMails;
	@JsonProperty("bcc")
	private List<String> blindCarbonCopyMails;
	private String subject;
	private MultipartFile file;
}
