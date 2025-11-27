package com.fredrik.enterprice_backend.todo_item.exceptions;

import java.util.UUID;

public class DuckTaskNotFoundException extends RuntimeException {
    public DuckTaskNotFoundException(UUID id) {

        super("DuckTask with id: " + id + " was not found");
    }
}
