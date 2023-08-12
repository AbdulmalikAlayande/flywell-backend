package com.example.airlinereservation.data.model.notifications;

import jakarta.persistence.Entity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TextMessage extends Notification{
	private String id;
	private String phoneNumber;
}
