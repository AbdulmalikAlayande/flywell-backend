package app.bola.flywell.data.model.auth;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends FlyWellModel {

    private String token;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Instant expiryDate;

    public boolean isExpired(){
        return this.expiryDate.isBefore(Instant.now());
    }
}
