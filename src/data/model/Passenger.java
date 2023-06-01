package data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
	private String id;
	private String firstName;
	private String lastName;
	private String fullName = firstName + lastName;
	private String userName;
	private String Email;
	private String phoneNumber;
	private String password;
	private String flightId;
}
