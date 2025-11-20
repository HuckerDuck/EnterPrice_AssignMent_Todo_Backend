package com.fredrik.enterprice_backend.security.controller;

import com.fredrik.enterprice_backend.security.jwt.JwtUtils;
import com.fredrik.enterprice_backend.user.dto.loginDuckRequestDTO;
import com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails.DuckDetails;
import com.fredrik.enterprice_backend.user.model.Duck;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        //? For the Debug
        System.out.println(authentication.getAuthorities());
        System.out.println("Class " + authentication.getClass());

        Object principal = authentication.getPrincipal();

        if (principal instanceof DuckDetails duckDetails) {
            System.out.println();
            System.out.println();
            System.out.println("The Duck is: " + duckDetails.getUsername());
            System.out.println("He has the right to: " + duckDetails.getAuthorities());
            System.out.println("  Is Accout Locked? " + duckDetails.isAccountNonLocked());
            System.out.println("  Is Account Enabled " + duckDetails.isEnabled());
            System.out.println("  Is Account Expired? " + duckDetails.isAccountNonExpired());
            System.out.println("  Is Credentials Expired? " + duckDetails.isCredentialsNonExpired());
            System.out.println("  Is the Password hashed? " + duckDetails.getPassword());
            System.out.println();
            System.out.println();
        } else {
            System.out.println("Principal Value" + principal);
        }

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
    }
