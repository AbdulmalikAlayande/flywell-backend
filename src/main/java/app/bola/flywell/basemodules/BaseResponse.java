package app.bola.flywell.basemodules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements Serializable {

    String publicId;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    String createdBy;
    String createdByRole;
    String lastModifiedBy;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("publicId", publicId)
                .add("createdDate", createdDate)
                .add("lastModifiedDate", lastModifiedDate)
                .add("createdBy", createdBy)
                .add("createdByRole", createdByRole)
                .add("lastModifiedBy", lastModifiedBy)
                .toString();
    }
}