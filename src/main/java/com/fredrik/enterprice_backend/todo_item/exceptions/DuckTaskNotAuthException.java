package com.fredrik.enterprice_backend.todo_item.exceptions;

public class DuckTaskNotAuthException extends RuntimeException{
        public DuckTaskNotAuthException(String action) {
            super("You are not allowed to " + action + " this DuckTask");
        }

}
