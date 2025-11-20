package com.fredrik.enterprice_backend.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {

        super("User with username: " + message + " was not found");;
    }
}
