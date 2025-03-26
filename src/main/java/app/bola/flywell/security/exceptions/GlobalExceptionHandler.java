package app.bola.flywell.security.exceptions;

import app.bola.flywell.dto.response.ErrorResponse;
import app.bola.flywell.exceptions.AuthenticationFailedException;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.exceptions.FailedRegistrationException;
import app.bola.flywell.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Map;
import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", errors.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidRequestException ex) {
        log.error(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Request", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
        log.error("Not Found Exception", ex);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(FailedRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleFailedRegistrationException(FailedRegistrationException ex) {
        log.error(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST, "Registration Failed", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login Failed: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST, "Malformed Request", "Invalid JSON format: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Unexpected Error", ex);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error", "An unexpected error occurred. Please contact support.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
