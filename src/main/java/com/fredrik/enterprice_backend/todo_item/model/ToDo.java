package com.fredrik.enterprice_backend.todo_item.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="todos")
@NoArgsConstructor
@AllArgsConstructor

public class ToDo {
    private Long id;
    private String title;
    private String description;

    private boolean completed;
    private LocalDateTime createdAt;
}
