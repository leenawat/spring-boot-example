package com.example.demo.controller;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponse.of("Invalid credentials", 400, "Bad Request", "/login"));
        } catch (Exception e) {
            log.error("Login error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.of("Authentication failed", 401, "Unauthorized", "/login"));
        }
    }

    @GetMapping("/me")
    public String me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}
