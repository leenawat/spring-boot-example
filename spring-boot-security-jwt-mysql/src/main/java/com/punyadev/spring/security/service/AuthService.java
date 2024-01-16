package com.punyadev.spring.security.service;

import com.punyadev.spring.security.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}