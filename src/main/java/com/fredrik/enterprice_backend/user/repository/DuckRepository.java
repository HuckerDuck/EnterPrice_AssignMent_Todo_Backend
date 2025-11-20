package com.fredrik.enterprice_backend.user.repository;

import com.fredrik.enterprice_backend.user.model.Duck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DuckRepository extends JpaRepository<Duck, UUID> {
    Optional<Duck> findByUsername(String username);
    Optional<Duck> findByEmail(String email);
}
