package com.fredrik.enterprice_backend.security.jwt;


import com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails.DuckDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private DuckDetailsService duckDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            //? Try to extract JWT from request header
            String jwt = jwtUtils.extractJwtFromRequest(request);

            // If the JWT is valid, set the authentication
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                // Get the Username from JWT token
                String username = jwtUtils.getUsernameFromJwtToken(jwt);

                // Then Load the user details from database
                UserDetails userDetails = duckDetailsService.loadUserByUsername(username);

                // Make a new authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()  // ← Inkluderar roll + permissions
                        );

                // Then seet the details
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Put the authenatication in the context
                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.debug("Set authentication for user: {}", username);
            }

        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        // Then continue the filter chain
        filterChain.doFilter(request, response);
    }
}