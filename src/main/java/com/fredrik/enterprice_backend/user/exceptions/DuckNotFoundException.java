package com.fredrik.enterprice_backend.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class DuckNotFoundException extends RuntimeException {
    public DuckNotFoundException(String username) {

        super("User with username: " + username + " was not found");;
    }
}
