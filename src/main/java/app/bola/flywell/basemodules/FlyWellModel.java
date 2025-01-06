package app.bola.flywell.basemodules;

import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class FlyWellModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String publicId;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    @Builder.Default
    private String createdBy = "SYSTEM";
    private String createdByRole;
    @LastModifiedBy
    private String lastModifiedBy;

    @PrePersist
    protected void onCreate() {
        if (publicId == null || publicId.isEmpty()) {
            publicId = UUID.randomUUID().toString();
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("publicId", publicId)
                .add("createdDate", createdDate)
                .add("lastModifiedDate", lastModifiedDate)
                .add("createdBy", createdBy)
                .add("createdByRole", createdByRole)
                .add("lastModifiedBy", lastModifiedBy)
                .toString();
    }
}
