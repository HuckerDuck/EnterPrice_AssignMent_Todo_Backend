package com.fredrik.enterprice_backend.user.Model;

import com.fredrik.enterprice_backend.user.Enum.CustomDuckRoles;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

//? Using Lombok for this is quite handy
@Setter
@Getter
@NoArgsConstructor
//? This is so that the datbas knows that this is a Enity
@Entity


@Table(name="users")
public class CustomDuck {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   //! Making sure that nowone can set or get the ID
   @Getter(AccessLevel.NONE)
   @Setter(AccessLevel.NONE)
   //! It's a UUID for security and it's also longer
   //! Unlike LONG id this will not go 1,2,3
   private UUID id;

   //? Setting it so that neither username or password can be false
   @Column(nullable = false)
   private String username;

   @Column(nullable = false)
   private String password;

   //? This is gonna make more since later
   //? But basically it telling the database
   //? Hey u, this is an Enum field so I want you to save it like that
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private CustomDuckRoles duckRoles = CustomDuckRoles.USER;

    //? You can't change the created_at when the user has been created
    @Setter(AccessLevel.NONE)
    @Column (name="created_at", nullable = false)
    private LocalDateTime createdAt;

    //? If I ever update a User I wanna know it
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;

    //? This is so that I can in the future disable a account
    //? Without having to actually drop (remove it) from the databae
    @Column(nullable = false)
    private Boolean enabled = true;

    //? This shouldn't really be needed but just in case enabled or duckroles
    //? Is empty then I want enable to be true and duckRoles to be of an admin
    //? The thought is to later manually add a Admin for security purposes
    @PrePersist
    void onCreatingTheDuck(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;

        if(enabled == null){
            this.enabled = true;
        }

        if(duckRoles == null){
            this.duckRoles = CustomDuckRoles.USER;
        }
    }

    //? So if you update a Duck then it will quack and put the localtime from right now
    @PreUpdate
    void onUpdatingADuck(){
        this.updatedAt = LocalDateTime.now();
    }





}
