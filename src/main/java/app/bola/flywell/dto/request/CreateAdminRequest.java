package app.bola.flywell.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties
public class CreateAdminRequest {

    String email;
    String password;
    String lastName;
    String firstName;
    String phoneNumber;

    @NotBlank(message = "This Field Cannot Be Empty Or Blank")
    String adminCode;

}
