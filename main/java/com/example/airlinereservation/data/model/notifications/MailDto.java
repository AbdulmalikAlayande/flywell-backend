package com.example.airlinereservation.data.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
	private Mailing sender;
	private List<Mailing> to;
	private String subject;
	private String htmlContent;
}
