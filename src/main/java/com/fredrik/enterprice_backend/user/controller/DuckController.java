package com.fredrik.enterprice_backend.user.controller;

import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.service.DuckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

    @GetMapping ("/username/{username}")
    public ResponseEntity<responseDuckDTO> findDuckByUsername(
            @PathVariable String username){
        responseDuckDTO foundDuck = duckService.findDuckByUserName(username);
        return ResponseEntity.ok(foundDuck);
    }

    @GetMapping ("/email/{email}")
    public ResponseEntity<responseDuckDTO> findDuckByEmail(
            @PathVariable String email){
        responseDuckDTO foundDuck = duckService.findDuckByEmail(email);
        return ResponseEntity.ok(foundDuck);
    }

    @GetMapping
    public ResponseEntity<List<responseDuckDTO>> getAllDucks(){
        List<responseDuckDTO> allDucks = duckService.getAllDucks();
        return ResponseEntity.ok(allDucks);
    }

    @DeleteMapping ("/disableUser/{username}")
    public ResponseEntity<Void> disableDuck(
            @PathVariable String username){
        duckService.disableDuck(username);
        return ResponseEntity.noContent().build();
    }




}
