package com.fredrik.enterprice_backend.user.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super("User with username: " + message + " already exists");
    }
}
