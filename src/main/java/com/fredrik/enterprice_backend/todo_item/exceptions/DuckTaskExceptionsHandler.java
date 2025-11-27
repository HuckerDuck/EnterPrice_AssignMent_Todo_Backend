package com.fredrik.enterprice_backend.todo_item.exceptions;

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
public class DuckTaskExceptionsHandler {
    private static final Logger logger = LoggerFactory.getLogger(DuckTaskExceptionsHandler.class);

    //? Duck aka User not logged in
    @ExceptionHandler(DuckNotLoggedInException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotLoggedIn(
            DuckNotLoggedInException exception,
            HttpServletRequest request) {

        logger.warn("User not logged in error: {}", exception.getMessage());

        List<ValidationError> errors = List.of(
                new ValidationError(
                        "authentication",
                        exception.getMessage()
                )
        );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.UNAUTHORIZED.value(),
                errors
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiErrorResponse);
    }

    //? Not Allowed to change the DuckTask
    @ExceptionHandler(DuckTaskNotAuthException.class)
    public ResponseEntity<ApiErrorResponse> notAllowedToChangeTask(
            DuckTaskNotAuthException exception,
            HttpServletRequest request) {

        logger.warn("User was allowed to change a delete / update: {}", exception.getMessage());

        List<ValidationError> errors = List.of(
                new ValidationError(
                        "authentication",
                        exception.getMessage()
                )
        );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.UNAUTHORIZED.value(),
                errors
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiErrorResponse);
    }

    //? Can't find the DuckTask that the user was looking for
    @ExceptionHandler(DuckTaskNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> couldNotFindTask(
            DuckTaskNotFoundException exception,
            HttpServletRequest request) {

        logger.warn("User was not able to find a DuckTask they were looking for: {}", exception.getMessage());

        List<ValidationError> errors = List.of(
                new ValidationError(
                        "authentication",
                        exception.getMessage()
                )
        );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                errors
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }




}
