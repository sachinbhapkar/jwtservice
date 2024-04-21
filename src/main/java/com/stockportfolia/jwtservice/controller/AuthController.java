package com.stockportfolia.jwtservice.controller;

import com.stockportfolia.jwtservice.dto.AuthRequestDTO;
import com.stockportfolia.jwtservice.dto.JwtResponseDTO;
import com.stockportfolia.jwtservice.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtServiceImpl jwtService;

    @PostMapping("/api/v1/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/api/v1/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
