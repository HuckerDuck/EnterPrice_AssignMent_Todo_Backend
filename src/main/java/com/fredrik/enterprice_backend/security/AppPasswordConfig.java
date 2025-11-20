package com.fredrik.enterprice_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//! Note for self, remember that all config classes
//! Usually need a @Configuration annotation
@Configuration
public class AppPasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 10 is the default for this
        // I have raised this to 12 for added Security reasons

        return new BCryptPasswordEncoder(12);
    }
}
