package com.fredrik.enterprice_backend.user.service;

import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.dto.updateDuckDTO;

import java.util.List;

public interface DuckService {
    //? Create a new Duck get back a responseDTO
    responseDuckDTO createDuck(createDuckDTO createDuckDTO);

    //? Find a Duck by username get back a responseDTO
    responseDuckDTO findDuckByUsername(String username);

    //? Find a Duck by email get back a responseDTO
    responseDuckDTO findDuckByEmail(String email);

    //? Update a Duck get back a responseDTO
    responseDuckDTO updateDuck(updateDuckDTO updateDuckDTO);

    //? Method for disabling a duck
    void disableDuck(String username);

    //? Method for getting all ducks in the database
    List<responseDuckDTO> getAllDucks();




}
