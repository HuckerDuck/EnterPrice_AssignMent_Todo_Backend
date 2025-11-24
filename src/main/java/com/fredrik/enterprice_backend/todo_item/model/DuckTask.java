package com.fredrik.enterprice_backend.todo_item.model;

import com.fredrik.enterprice_backend.todo_item.emums.ToDoPriority;
import com.fredrik.enterprice_backend.user.model.Duck;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="DuckTask")
@NoArgsConstructor
@AllArgsConstructor
public class DuckTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToDoPriority priority;


    private Boolean completed;

    // @UpdateTimeStampe is used so that automaticly sets the time
    // when the object is updated
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // @CreateTimeStamp is used so that automaticly sets the time
    // when the object is created
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    //? This is the important bit
    @ManyToOne
    //? This will try to connect each TODO to a duck onto it's ID
    @JoinColumn(name = "duck_id", nullable = false)
    private Duck duck;
}
