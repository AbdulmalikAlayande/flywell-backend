package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import app.bola.flywell.data.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * Response DTO for {@link User}
 */

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminResponse extends BaseResponse {

}
