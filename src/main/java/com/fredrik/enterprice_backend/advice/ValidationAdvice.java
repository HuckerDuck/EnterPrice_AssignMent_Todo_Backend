package com.fredrik.enterprice_backend.advice;

import com.fredrik.enterprice_backend.advice.DTO.ApiErrorResponse;
import com.fredrik.enterprice_backend.advice.DTO.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ValidationAdvice {
    //? Adding a Logger so it can be used here
    private static final Logger logger = LoggerFactory.getLogger(ValidationAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ApiErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ){
        List<ValidationError> errorDetailList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        fieldError -> new ValidationError(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()))
                .toList();

        logger.warn("Validation failed: {} errors → {}",
                errorDetailList.size(),
                errorDetailList.stream().map(ValidationError::field).toList()
        );

        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        request.getRequestURI(),
                        HttpStatus.BAD_REQUEST.value(),
                        errorDetailList
                )
        );
    }
}
