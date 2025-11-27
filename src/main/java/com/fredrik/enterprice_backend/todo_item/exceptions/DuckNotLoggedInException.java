package com.fredrik.enterprice_backend.todo_item.exceptions;

public class DuckNotLoggedInException extends RuntimeException {
    public DuckNotLoggedInException() {

        super("User is not logged in");
    }
}
