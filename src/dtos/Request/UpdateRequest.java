package dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateRequest {
	@Nullable private String firstName;
	@Nullable private String lastName;
	@Nullable private String Email;
	@Nullable private String phoneNumber;
	@Nullable private String password;
	@Nullable private String newUserName;
	@NotNull private String userName;
}
