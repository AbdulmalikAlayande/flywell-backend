package app.bola.flywell.dto.request;

import app.bola.flywell.data.model.auth.RefreshToken;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


/**
 * Request DTO for {@link RefreshToken} model
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenRequest {

    String refreshToken;

}
