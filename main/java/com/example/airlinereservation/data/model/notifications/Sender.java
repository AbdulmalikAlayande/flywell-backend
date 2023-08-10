package com.example.airlinereservation.data.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Sender {
	
	public String senderEmail;
	public String senderPhoneNumber;
}
