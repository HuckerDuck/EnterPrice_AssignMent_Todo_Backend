package com.fredrik.enterprice_backend.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.fredrik.enterprice_backend.user.enums.DuckPermissions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public enum DuckRoles {
    USER(
            DuckRoleNames.USER.getRoleName(),
            Set.of(
                    READ,
                    WRITE,
                    DELETE
            )
    ),

    ADMIN (
            DuckRoleNames.ADMIN.getRoleName(),
    Set.of(
            READ,
            WRITE,
            DELETE
    )

    );

    private final String roleName;
    private final Set<DuckPermissions> duckPermissions;

    public List<SimpleGrantedAuthority> getDuckAuthorities(){
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(this.roleName));
        authorityList.addAll(this.duckPermissions.stream().map(
                duckPermissions->new SimpleGrantedAuthority(
                        duckPermissions.getCustomDuckPermission())
        ).toList()
        );

        return authorityList;
    }


}
