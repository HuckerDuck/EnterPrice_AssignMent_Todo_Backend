package com.fredrik.enterprice_backend.advice.DTO;

import java.time.LocalDateTime;

public record ValidationError (
        String field,
        String message
) {

}
