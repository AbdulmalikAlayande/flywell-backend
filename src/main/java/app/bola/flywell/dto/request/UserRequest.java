package app.bola.flywell.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email(message = "Please enter a valid email format", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Size(max = 15, min = 8, message = "Invalid Password Length: Password length must be between 8 and 15 characters")
    private String password;
}
