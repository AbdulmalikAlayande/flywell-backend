package com.example.airlinereservation.dtos.Request;


import com.example.airlinereservation.data.model.notifications.Recipients;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	private List<Recipients> to;
	private List<String> carbonCopyMails;
	private String phoneNumber;
	private String username;
}
