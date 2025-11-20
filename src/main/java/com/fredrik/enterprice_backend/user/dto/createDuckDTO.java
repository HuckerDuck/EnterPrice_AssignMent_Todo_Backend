package com.fredrik.enterprice_backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record createDuckDTO (
        @NotBlank
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        @Pattern(
                regexp = "^" +
                        "(?=.*[a-z])" +
                        "(?=.*[A-Z])" +
                        "(?=.*[0-9])" +
                        "(?=.*[ @$!%*?&])" +
                        ".+$",
                message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character"
        )
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        String password,

        @NotBlank
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @Email(message = "Email must be a valid email")
        String email

){
}
