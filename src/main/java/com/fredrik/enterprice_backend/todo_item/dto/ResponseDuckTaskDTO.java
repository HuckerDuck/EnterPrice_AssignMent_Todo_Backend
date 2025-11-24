package com.fredrik.enterprice_backend.todo_item.dto;

import com.fredrik.enterprice_backend.todo_item.emums.ToDoPriority;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseDuckTaskDTO (
        // We make the id public so we can use it in the response
        // Then we can use that id to update the task

        UUID id,
        String title,
        String description,
        String priority,
        Boolean completed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
