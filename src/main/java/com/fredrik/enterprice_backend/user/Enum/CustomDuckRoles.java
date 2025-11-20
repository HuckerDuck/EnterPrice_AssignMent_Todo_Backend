package com.fredrik.enterprice_backend.user.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.fredrik.enterprice_backend.user.Enum.CustomDuckPermissions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public enum CustomDuckRoles {
    USER(
            CustomDuckRoleName.USER.getRoleName(),
            Set.of(
                    READ,
                    WRITE,
                    DELETE
            )
    ),

    ADMIN (
            CustomDuckRoleName.ADMIN.getRoleName(),
    Set.of(
            READ,
            WRITE,
            DELETE
    )

    );

    private final String roleName;
    private final Set<CustomDuckPermissions> duckPermissions;

    public List<SimpleGrantedAuthority> getDuckAuthorites (){
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
