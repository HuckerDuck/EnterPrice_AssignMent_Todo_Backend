package com.fredrik.enterprice_backend.user.exceptions;

public class DuckAlreadyExistsException extends RuntimeException {
    public DuckAlreadyExistsException(String message) {
        super("User with username: " + message + " already exists");
    }
}
