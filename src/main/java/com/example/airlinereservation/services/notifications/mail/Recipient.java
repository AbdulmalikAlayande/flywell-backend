package com.example.airlinereservation.services.notifications.mail;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipient{
  
    private String id;
    private String email;
    private String phoneNumber;
}
