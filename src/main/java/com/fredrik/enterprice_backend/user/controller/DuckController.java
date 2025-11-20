package com.fredrik.enterprice_backend.user.controller;

import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.service.DuckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/ducks")
@RestController
public class DuckController {
    private final DuckService duckService;

    @PostMapping("/register")
    public ResponseEntity<responseDuckDTO> registerDuck(
            @Valid @RequestBody
            createDuckDTO createDuckDTO){
        responseDuckDTO createdDuck = duckService.createDuck(createDuckDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDuck);
    }


}
