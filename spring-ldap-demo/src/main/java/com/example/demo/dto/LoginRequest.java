package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

