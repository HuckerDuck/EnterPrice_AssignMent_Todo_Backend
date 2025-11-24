package com.fredrik.enterprice_backend.todo_item.dto;

import com.fredrik.enterprice_backend.todo_item.emums.ToDoPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDuckTaskDTO (

        //? For this CreateDuckTask we don't need the id
        // We only require the title, description and priority

        @NotBlank
        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String title,

        @NotBlank
        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String description,

        @NotNull
        ToDoPriority priority
) {
}
