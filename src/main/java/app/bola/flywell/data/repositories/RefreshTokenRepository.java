package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.auth.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends FlyWellRepository<RefreshToken> {

    boolean existsByToken(String token);
    Optional<RefreshToken> findByToken(String token);
}
