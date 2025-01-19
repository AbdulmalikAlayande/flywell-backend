package app.bola.flywell.controllers;

import app.bola.flywell.dto.request.LoginRequest;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.security.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String refreshToken, String accessToken) {
        String response = authService.logout(refreshToken, accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
