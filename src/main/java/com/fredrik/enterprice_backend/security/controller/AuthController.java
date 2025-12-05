package com.fredrik.enterprice_backend.security.controller;

import com.fredrik.enterprice_backend.security.jwt.JwtUtils;
import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.loginDuckRequestDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails.DuckDetails;
import com.fredrik.enterprice_backend.user.model.Duck;
import com.fredrik.enterprice_backend.user.service.DuckService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final DuckService duckService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateTheDuck(
            @RequestBody loginDuckRequestDTO loginDuckRequestDTO,
            HttpServletResponse response
            ) {
        logger.debug("Attempting authentication for user: {}", loginDuckRequestDTO.username());

        //? Perform a authentication of the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDuckRequestDTO.username(),
                        loginDuckRequestDTO.password()
                )
        );

        Object principal = authentication.getPrincipal();

        DuckDetails duckDetails = (DuckDetails) authentication.getPrincipal();
        Duck duck = duckDetails.getDuck();

        //? Generate a token for the user
        String jwtToken = jwtUtils.generateJwtToken(duck);

        //? Set a Cookie, this is really for a web brower though
        Cookie cookie = new Cookie("authToken", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        //? Set this to true in a production setting
        //? Where you are gonna use HTTPS instead of HTTP
        cookie.setSecure(false);
        cookie.setMaxAge(3600);
        //? This is for a CSRF Protection
        cookie.setAttribute("SameSite", "LAX");
        response.addCookie(cookie);

        //? This is optional but it is good practice to return the token
        return ResponseEntity.ok(Map.of
                ("username", loginDuckRequestDTO.username(),
                        "email", duck.getEmail(),
                        "authorites", duckDetails.getAuthorities(),
                        "role", duck.getDuckRoles().name(),
                        "token", jwtToken


                ));


    }

    @PostMapping("/register")
    public ResponseEntity<responseDuckDTO> registerDuck(
            @Valid @RequestBody
            createDuckDTO createDuckDTO){
        responseDuckDTO createdDuck = duckService.createDuck(createDuckDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDuck);
    }

    //? This is mainly to log out the user from PostMan
    //? The frontend will handle the logout itself
    //? It will save the jwt token and logout from the frontend itself
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Create a cookie with the name authToken and an empty value
        Cookie cookie = new Cookie("authToken", null);


        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(false);

        //? By setting the maxage of this to 0
        //? The cookie will be deleted immediately
        cookie.setMaxAge(0);
        cookie.setAttribute("SameSite", "LAX");


        response.addCookie(cookie);

        // We use the logger to log that we have added it
        logger.info("Logout method was called, authToken cookie cleared.");

        // We send back a simple message
        return ResponseEntity.ok(Map.of(
                "message", "Du lyckades logga ut "
        ));
    }


    }
