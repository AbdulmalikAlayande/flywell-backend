package app.bola.flywell.security.services;

import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.UserRepository;
import app.bola.flywell.dto.request.LoginRequest;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.security.providers.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private JwtTokenProvider tokenProvider;


    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String refreshToken = tokenProvider.generateRefreshToken(userDetails);
        String accessToken = tokenProvider.generateAccessToken(userDetails);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User with email "+username));

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return toResponse(user.getPublicId(), refreshToken, accessToken);
    }

    private LoginResponse toResponse(String userId, String refreshToken, String accessToken) {
        return LoginResponse.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }

    public String logout(String refreshToken, String accessToken) {
        return null;
    }
}
