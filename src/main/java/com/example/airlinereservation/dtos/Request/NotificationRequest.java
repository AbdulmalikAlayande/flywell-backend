package com.example.airlinereservation.dtos.Request;


import com.example.airlinereservation.data.model.notifications.Recipients;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	
	private String firstName;
	private long OTP;
	private String lastName;
	private String email;
}
