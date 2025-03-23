package app.bola.flywell.security.services;

import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.UserRepository;
import app.bola.flywell.dto.request.LoginRequest;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.exceptions.AuthenticationFailedException;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.security.providers.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.bola.flywell.security.models.FlyWellUserPrincipal;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    @Lazy
    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final JwtTokenProvider tokenProvider;
    final Logger logger = LoggerFactory.getLogger(AuthService.class);
    final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationFailedException {
        logger.info("Attempting login for user: {}", loginRequest.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            FlyWellUserPrincipal userPrincipal = (FlyWellUserPrincipal) authentication.getPrincipal();
            User user = userRepository.findByEmail(userPrincipal.getUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));

            String accessToken = tokenProvider.generateToken(userPrincipal);
            String refreshToken = tokenProvider.generateToken(userPrincipal);

            user.setRefreshToken(refreshToken);
            userRepository.save(user);

            logger.info("User {} {}, Email: {} successfully logged in", user.getFirstName(), user.getLastName(), user.getEmail());

            return toResponse(user.getPublicId(), refreshToken, accessToken);
        }catch (Exception e) {
            logger.warn("Failed login attempt for user: {}", loginRequest.getEmail());
            logger.error("Login Attempt Failed:: {}", e.getMessage());

            throw new AuthenticationFailedException("Invalid username or password");
        }
    }

    private LoginResponse toResponse(String userId, String refreshToken, String accessToken) {
        return LoginResponse.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }

    public String logout(String refreshToken, String accessToken) {
        logger.info("Processing logout request");

        try {
            String username = tokenProvider.extractUsername(refreshToken);
            if (username != null) {
                User user = userRepository.findByEmail(username)
                        .orElse(null);

                if (user != null && refreshToken.equals(user.getRefreshToken())) {
                    user.setRefreshToken(null);
                    userRepository.save(user);

                    logger.info("User {} successfully logged out", username);

                    SecurityContextHolder.clearContext();
                    return "Logout successful";
                }
            }
        } catch (Exception e) {
            logger.error("Error during logout: {}", e.getMessage());
        }

        // If the code gets here, either the tokens were invalid or we couldn't find the user
        // We will still have to clear the security context to be safe
        SecurityContextHolder.clearContext();
        logger.info("Completed logout process");

        return "Logout successful";
    }

    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        FlyWellUserPrincipal userPrincipal = (FlyWellUserPrincipal) authentication.getPrincipal();
        return userRepository.findByEmail(userPrincipal.getUsername());
    }

    public boolean isPasswordValid(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
