package com.fredrik.enterprice_backend.user.service;

import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.dto.updateDuckDTO;
import com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails.DuckDetailsService;
import com.fredrik.enterprice_backend.user.mapper.DuckMapper;
import com.fredrik.enterprice_backend.user.model.Duck;
import com.fredrik.enterprice_backend.user.repository.DuckRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DuckServiceImpl implements DuckService{
    private final DuckRepository duckRepository;
    private final DuckDetailsService duckDetailsService;
    private final DuckMapper duckMapper;

    // This will work in a way where it will tell Spring
    // To scan for password encoder, then it will go into
    // The @Configuration class and find the PasswordEncoder
    // And then it will inject it here
    private final PasswordEncoder passwordEncoder;

    //? --  Adding the methods am gonna use from the Interface under this --

    //?
    //?                     --- Create a Duck ---
    //?
    @Override
    public responseDuckDTO createDuck(createDuckDTO createDuckDTO) {
        //? Use the mapper to convert from a DTO -> Entity
        Duck duck = duckMapper.toEntity(createDuckDTO);

        //? Use the encoder and decrypt the password
        duck.setPassword(passwordEncoder.encode(duck.getPassword()));

        //? Save the entity (aka Duck) to the database
        Duck savedDuck = duckRepository.save(duck);

        //? Use the mapper to convert from an Entity -> DTO
        //? For security reason we use the responseDTO to return what is
        //? Being given back in the response so that we can't see
        //? The Ducks personal password
        return duckMapper.toResponseDTO(savedDuck);
    }

    //?
    //?               --- Find a Duck by Username ---
    //?

    @Override
    public responseDuckDTO findDuckByUsername(String username) {
        Duck duck = duckRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found with username: " + username + "Was not found")
                );

        return duckMapper.toResponseDTO(duck);
    }

    //?
    //?                     --- Find a Duck by Email ---
    //?

    @Override
    public responseDuckDTO findDuckByEmail(String email) {
        Duck duck = duckRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found with email: " + email + "Was not found")
                );

        return duckMapper.toResponseDTO(duck);
    }

    //?
    //?                     --- Update a Duck ---
    //?

    @Override
    public responseDuckDTO updateDuck(updateDuckDTO updateDuckDTO) {
        return null;
    }

    //?
    //?                     --- Disable a Duck ---
    //?

    @Override
    public void disableDuck(String username) {

    }


    //?
    //?                --- Get a list of all Ducks in the system ---
    //?

    @Override
    public List<responseDuckDTO> getAllDucks() {
        return List.of();
    }
}
