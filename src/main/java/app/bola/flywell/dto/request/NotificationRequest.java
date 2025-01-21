package app.bola.flywell.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	
	private String firstName;
	private long OTP;
	private String code;
	private String email;
	private String mailPath;
	private String mailSubject;
}
