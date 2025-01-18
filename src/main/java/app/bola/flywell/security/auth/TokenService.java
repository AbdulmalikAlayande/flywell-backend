package app.bola.flywell.security.auth;

import app.bola.flywell.data.model.auth.RefreshToken;
import app.bola.flywell.data.repositories.RefreshTokenRepository;
import app.bola.flywell.dto.request.TokenRequest;
import app.bola.flywell.dto.response.TokenResponse;
import app.bola.flywell.security.providers.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
@AllArgsConstructor
public class TokenService {

    final JwtTokenProvider tokenProvider;
    final RefreshTokenRepository tokenRepository;


    public TokenResponse refreshAccessToken(@NotNull TokenRequest tokenRequest) {

        RefreshToken refreshToken = tokenRepository.findByToken(tokenRequest.getRefreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Refresh refreshToken not found"));

        if (refreshToken.isExpired()) {
            throw new IllegalArgumentException("Refresh refreshToken is expired");
        }

        String accessToken = tokenProvider.generateAccessToken(refreshToken.getUser());
        String newRefreshToken = tokenProvider.generateRefreshToken(refreshToken.getUser());

        refreshToken.setToken(newRefreshToken);
        refreshToken.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS));
        RefreshToken updatedRefreshToken = tokenRepository.save(refreshToken);

        return new TokenResponse(accessToken, updatedRefreshToken.getToken());
    }
}
