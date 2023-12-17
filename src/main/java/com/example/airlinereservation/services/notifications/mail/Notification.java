package com.example.airlinereservation.services.notifications.mail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Notification {
    private String message;
    @JsonProperty("sender")
    private Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;
    @JsonProperty("htmlContent")
    private String htmlContent;
    @JsonProperty(value = "subject")
    private String subject;
}
