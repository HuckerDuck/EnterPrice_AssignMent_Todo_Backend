package com.fredrik.enterprice_backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record updateDuckDTO (
        @Size(min = 3, max = 50, message = "Username must be betweeen 3 and 50 charecters ")
        String username,

        @Email (message = "Email must be a valid email")
        String email
){
}
