package com.example.airlinereservation.dtos.Request;


import com.example.airlinereservation.data.model.notifications.Recipients;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	private List<Recipients> recipients;
	private List<String> carbonCopyMails;
	private String phoneNumber;
	private String username;
}
