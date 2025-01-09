package app.bola.flywell.basemodules;

import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class FlyWellModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String publicId;

    @Version
    private int version;


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
                .toString();
    }
}
