package com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails;

import com.fredrik.enterprice_backend.user.model.Duck;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class DuckDetails implements UserDetails {
    private final Duck duck;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + duck.getDuckRoles().name()));
    }

    @Override
    public String getPassword() {
        return duck.getPassword();
    }

    @Override
    public String getUsername() {
        return duck.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return duck.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return duck.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return duck.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return duck.isEnabled();
    }
}
