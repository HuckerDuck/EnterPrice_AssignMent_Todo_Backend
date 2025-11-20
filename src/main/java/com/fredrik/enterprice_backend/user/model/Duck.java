package com.fredrik.enterprice_backend.user.model;

import com.fredrik.enterprice_backend.user.enums.DuckRoles;
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


@Table(name="ducks")
public class Duck {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   //! Making sure that nowone can set or get the ID

   @Setter(AccessLevel.NONE)
   //! It's a UUID for security and it's also longer
   //! Unlike LONG id this will not go 1,2,3
   private UUID id;

   //? Setting it so that neither username or password can be false
   @Column(nullable = false)
   private String username;

   @Column(nullable = false)
   private String password;

   //? Adding a email for the user and making sure it's unique
   @Column(nullable = false, unique = true)
   private String email;

   //? This is gonna make more since later
   //? But basically it telling the database
   //? Hey u, this is an Enum field so I want you to save it like that
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private DuckRoles duckRoles = DuckRoles.USER;

    //? You can't change the created_at when the user has been created
    @Setter(AccessLevel.NONE)
    @Column (name="created_at", nullable = false)
    private LocalDateTime createdAt;

    //? If I ever update a User I wanna know it
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;

    //! Removing the is{text}
    //! Lombok will automaticly when getting a boolean add the
    //! Is, so if there is a is in the name it might be a double is
    //! Or just ignore the is

    //? This is so that I can in the future disable a account
    //? Without having to actually drop (remove it) from the databae
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    //? This shouldn't really be needed but just in case enabled or duckroles
    //? Is empty then I want enable to be true and duckRoles to be of an admin
    //? The thought is to later manually add a Admin for security purposes
    @PrePersist
    void onCreatingTheDuck(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;



        if(duckRoles == null){
            this.duckRoles = DuckRoles.USER;
        }
    }

    //? So if you update a Duck then it will quack and put the localtime from right now
    @PreUpdate
    void onUpdatingADuck(){
        this.updatedAt = LocalDateTime.now();
    }






}
