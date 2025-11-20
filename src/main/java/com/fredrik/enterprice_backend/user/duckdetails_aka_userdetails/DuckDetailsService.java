package com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails;

import com.fredrik.enterprice_backend.user.model.Duck;
import com.fredrik.enterprice_backend.user.repository.DuckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DuckDetailsService implements UserDetailsService {
    private final DuckRepository duckRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Duck duck = duckRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username + "Was not found")
                );

        return new DuckDetails(duck);
    }
}
