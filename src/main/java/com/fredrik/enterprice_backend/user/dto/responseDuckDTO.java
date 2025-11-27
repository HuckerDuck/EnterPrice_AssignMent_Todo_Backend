package com.fredrik.enterprice_backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record responseDuckDTO (
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @NotBlank(message = "Username can't be blank, empty or contain whitespaces")
        String username,

        boolean enabled
){
}
