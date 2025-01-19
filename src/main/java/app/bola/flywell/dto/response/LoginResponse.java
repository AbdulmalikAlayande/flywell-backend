package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse extends BaseResponse {

    String userId;
    String refreshToken;
    String accessToken;

}
