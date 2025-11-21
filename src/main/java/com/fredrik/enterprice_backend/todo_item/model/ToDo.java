package com.fredrik.enterprice_backend.todo_item.model;

import com.fredrik.enterprice_backend.todo_item.emums.ToDoPriority;
import com.fredrik.enterprice_backend.user.model.Duck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="todos")
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToDoPriority priority;


    private Boolean completed;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    //? This is the important bit
    @ManyToOne
    //? This will try to connect each TODO to a duck onto it's ID
    @JoinColumn(name = "duck_id", nullable = false)
    private Duck duck;

    @PreUpdate
    void onUpdatingATodo(){
        this.createdAt = LocalDateTime.now();
    }
}
