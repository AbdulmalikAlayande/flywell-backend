package app.bola.flywell.controllers;

import app.bola.flywell.dto.request.TokenRequest;
import app.bola.flywell.dto.response.TokenResponse;
import app.bola.flywell.security.auth.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    final TokenService tokenService;

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> generateRefreshToken(TokenRequest request) {
        TokenResponse tokenResponse = tokenService.refreshAccessToken(request);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    };
}
