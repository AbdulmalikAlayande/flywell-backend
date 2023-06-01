package dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {
	private String firstName;
	private String lastName;
	private String Email;
	private String phoneNumber;
	private String password;
	private String userName;
}
