package app.bola.flywell.security.exceptions;

import app.bola.flywell.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("Access Denied Exception", ex);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.FORBIDDEN, "Access Denied", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        log.error(ex.getMessage());
        log.error(ex.getCause().toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed: " + ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        log.error("Invalid Credentials", ex);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Credentials", "The username or password you entered is incorrect.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
