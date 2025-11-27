package com.fredrik.enterprice_backend.user.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String message) {
        super("Email with emailaddress: " + message + " already exists");
    }
}
