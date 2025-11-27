package com.fredrik.enterprice_backend.todo_item.dto;

import com.fredrik.enterprice_backend.todo_item.emums.ToDoPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDuckTaskDTO (
        // Since CreatedAt and UpdatedAt are not editable we don't need them in the update DTO
        // They are set automaticlly anyway so
        @NotBlank
        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String title,

        @NotBlank
        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String description,

        @NotNull
        ToDoPriority priority,

        @NotNull
        Boolean completed

) {
}
