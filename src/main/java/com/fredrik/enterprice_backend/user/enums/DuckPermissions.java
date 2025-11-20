package com.fredrik.enterprice_backend.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DuckPermissions {
    READ("READ"),
    WRITE("WRITE"),
    DELETE("DELETE");

    private final String customDuckPermission;
}
