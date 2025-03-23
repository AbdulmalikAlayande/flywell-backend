package app.bola.flywell.security.exceptions;

import app.bola.flywell.exceptions.AuthenticationFailedException;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.exceptions.FailedRegistrationException;
import app.bola.flywell.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleInvalidRequest(InvalidRequestException ex) {
        log.error(ex.getMessage());
        log.error(ex.getCause().toString());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(Exception ex) {
        log.error(ex.getMessage());
        log.error(ex.getCause().toString());
        return new ResponseEntity<>("Entity Not Found: %s".formatted(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage());
        log.error(ex.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource Not Found: " + ex.getMessage());
    }

    @ExceptionHandler(FailedRegistrationException.class)
    public ResponseEntity<String> handleFailedRegistrationException(FailedRegistrationException ex) {
        log.error(ex.getMessage());
        log.error(ex.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration Failed: " + ex.getMessage());
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        log.error(ex.getMessage());
        log.error(ex.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login Failed: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseError(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body("Malformed JSON request: " + ex.getLocalizedMessage());
    }
}
