package com.fredrik.enterprice_backend.user.exceptions;

import com.fredrik.enterprice_backend.advice.DTO.ApiErrorResponse;
import com.fredrik.enterprice_backend.advice.DTO.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class DuckExceptionsHandler {
    private static final Logger logger = LoggerFactory.getLogger(DuckExceptionsHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyExists(
            UserAlreadyExistsException exception,
            HttpServletRequest request) {

        logger.warn("User Already exists error: {}",exception.getMessage());

        List<ValidationError> errors = List.of(
                new ValidationError(
                        "username",
                        exception.getMessage()
                )
        );





        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponse);


    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFound(
            UserNotFoundException exception,
            HttpServletRequest request
    ){
        logger.warn("User was not found with error: {}", exception.getMessage());

        List<ValidationError> errors = List.of(
                new ValidationError(
                        "username",
                        exception.getMessage()
                )
        );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }


    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExist(
            EmailAlreadyExistException exception,
            HttpServletRequest request
    ){
        logger.warn ("Email was already in use error: {}", exception.getMessage());

        List<ValidationError> errors = List.of(
                new ValidationError(
                        "email",
                        exception.getMessage()
                )
        );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponse);
    }
}
