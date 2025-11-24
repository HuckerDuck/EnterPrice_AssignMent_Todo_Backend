package com.fredrik.enterprice_backend.security.jwt;

import com.fredrik.enterprice_backend.user.enums.DuckRoles;
import com.fredrik.enterprice_backend.user.model.Duck;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // This is gonna read the JWT token from a enviroment variable
    @Value("${jwt.secret}")
    private String base64EncodedSecretKey;


    //? Default for now is one hour
    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationMs;

    // Get the signing key with validation as well
    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(base64EncodedSecretKey);

        //? Validate the the key is at least 256 bits long
        if (keyBytes.length < 32) {
            throw new IllegalStateException("JWT secret must be at least 256 bits after Base64 decode");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate at token with all the information from Duck
    public String generateJwtToken(Duck duck) {
        logger.debug("Generating JWT for user: {} with role: {}", duck.getUsername(), duck.getDuckRoles());


        List<String> authorities = duck.getDuckRoles()
                .getDuckAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = Jwts.builder()
                .subject(duck.getUsername())
                .claim("authorities", authorities)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();

        logger.info("JWT generated successfully for user: {} with authorities: {}", duck.getUsername(), authorities);
        return token;
    }

    // Get the username from JWT token
    public String getUsernameFromJwtToken(String token) {
        try {
            System.out.println("Got the Username from JWT");
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.getSubject();
            logger.debug("Extracted username '{}' from JWT token", username);
            return username;

        } catch (Exception e) {
            System.out.println("FAIL: It didn't get to extract username!");
            System.out.println("Message: " + e.getMessage());
            logger.warn("Failed to extract username from token: {}", e.getMessage());
            return null;
        }
    }

    // Get all the authorities from the JWT token
    public List<SimpleGrantedAuthority> getAuthoritiesFromJwtToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            List<?> authoritiesClaim = claims.get("authorities", List.class);

            if (authoritiesClaim == null || authoritiesClaim.isEmpty()) {
                logger.warn("No authorities found in JWT token");
                return List.of();
            }

            // Konvert it to a List of SimpleGrantedAuthority
            List<SimpleGrantedAuthority> authorities = authoritiesClaim.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            logger.debug("Extracted authorities from JWT token: {}", authorities);
            return authorities;

        } catch (Exception e) {
            logger.error("Failed to extract authorities from token: {}", e.getMessage());
            return List.of();
        }
    }

    // Get only the role from the JWT token
    public DuckRoles getRoleFromJwtToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            List<?> authoritiesClaim = claims.get("authorities", List.class);

            if (authoritiesClaim == null || authoritiesClaim.isEmpty()) {
                logger.warn("No authorities found in JWT token");
                return DuckRoles.USER;
            }

            // Find the authority that begins with Role_
            String roleString = authoritiesClaim.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .filter(auth -> auth.startsWith("ROLE_"))
                    .findFirst()
                    .map(role -> role.replace("ROLE_", ""))
                    .map(String::toUpperCase)
                    .orElse("USER");

            DuckRoles role = DuckRoles.valueOf(roleString);
            logger.debug("Extracted role from JWT token: {}", role);
            return role;

        } catch (Exception e) {
            logger.error("Failed to extract role from token: {}", e.getMessage());
            return DuckRoles.USER;
        }
    }

    // Validate the JWT token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);

            logger.debug("JWT validation succeeded");
            return true;

        } catch (Exception e) {
            logger.error("JWT validation failed: {}", e.getMessage());
        }

        return false;
    }

    // This is a helper method to extract the JWT from a cookie
    public String extractJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("authToken".equals(cookie.getName())) {
                logger.debug("JWT found in cookie");
                return cookie.getValue();
            }
        }
        return null;
    }

    // Helping method to extract JWT from header
    public String extractJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            logger.debug("JWT found in Authorization header");
            return header.substring(7);
        }
        return null;
    }

    // Another helping method to extract JWT from request
    public String extractJwtFromRequest(HttpServletRequest request) {
        //? We try with the header first, usually the way you do it
        //? From a Frontend, you wanna do JSON
        String jwt = extractJwtFromHeader(request);

        // Om inte i header, försök cookie
        if (jwt == null) {
            jwt = extractJwtFromCookie(request);
        }

        return jwt;
    }
}