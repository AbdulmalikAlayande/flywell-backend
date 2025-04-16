package app.bola.flywell.basemodules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Base Response class for all response DTOs, it is corresponding to {@link FlyWellModel}
 */


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements Serializable {

    String publicId;
    String message;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("publicId", publicId)
                .add("message", message)
                .toString();
    }
}