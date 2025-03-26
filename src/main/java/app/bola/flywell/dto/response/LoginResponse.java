package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("publicId", getPublicId())
                .add("message", getMessage())
                .add("userId", userId)
                .add("refreshToken", refreshToken)
                .add("accessToken", accessToken)
                .toString();
    }
}
