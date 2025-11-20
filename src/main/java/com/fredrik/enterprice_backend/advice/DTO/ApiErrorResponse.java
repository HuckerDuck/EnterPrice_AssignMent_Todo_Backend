package com.fredrik.enterprice_backend.advice.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse (
        // When it happend
        LocalDateTime timestamp,
        //? Path, what controller endpoint it happend on
        String path,
        int status,
        List<ValidationError> errorDetailList
) {


}
