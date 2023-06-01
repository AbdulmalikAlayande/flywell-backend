package dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerResponse {
	private String fullName;
	private String Email;
	private String phoneNumber;
	private String id;
	private String userName;
	private String flightId;
}
