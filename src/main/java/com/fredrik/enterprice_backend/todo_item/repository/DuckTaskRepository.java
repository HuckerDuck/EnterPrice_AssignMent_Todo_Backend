package com.fredrik.enterprice_backend.todo_item.repository;

import com.fredrik.enterprice_backend.todo_item.model.DuckTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DuckTaskRepository extends JpaRepository<DuckTask, UUID> {
    List<DuckTask> findAllByUserId(UUID userId);
}
