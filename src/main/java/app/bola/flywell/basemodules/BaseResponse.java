package app.bola.flywell.basemodules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base Response class for all response DTOs corresponding to {@link FlyWellModel}
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


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("publicId", publicId)
                .toString();
    }
}