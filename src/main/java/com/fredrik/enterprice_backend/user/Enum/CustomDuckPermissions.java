package com.fredrik.enterprice_backend.user.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomDuckPermissions {
    READ("READ"),
    WRITE("WRITE"),
    DELETE("DELETE");

    private final String customDuckPermission;
}
