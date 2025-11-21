package com.fredrik.enterprice_backend.todo_item.emums;

import lombok.AllArgsConstructor;
import lombok.Getter;


//? Using Lombok to make the code cleaner
//? Adding getter and a AllArgsConstructor
@AllArgsConstructor
@Getter
//? This is gonna be a priority List for the TODO task
public enum ToDoPriority {
    //? 1 is the lowest priority and 6 is the highest priority
    QUACKLING (1, "Quackling"),
    LITTLE_DUCK (2, "Little Duck"),
    DUCK (3, "Duck"),
    BIG_DUCK (4, "Big Duck"),
    MAMMA_DUCK(5, "Mamma Duck"),
    PAPPA_DUCK(6, "Pappa Duck");


    private final int level;
    private final String displayName;




}

