package app.bola.flywell.controllers;

import app.bola.flywell.dto.request.LoginRequest;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.exceptions.AuthenticationFailedException;
import app.bola.flywell.security.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws AuthenticationFailedException {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String refreshToken, String accessToken) {
        String response = authService.logout(refreshToken, accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
