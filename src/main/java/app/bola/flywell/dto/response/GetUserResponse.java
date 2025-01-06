package app.bola.flywell.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserResponse {
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNumber;
}
