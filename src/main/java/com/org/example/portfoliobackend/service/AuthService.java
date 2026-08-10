package com.org.example.portfoliobackend.service;

import com.org.example.portfoliobackend.dto.auth.AuthResponse;
import com.org.example.portfoliobackend.dto.auth.LoginRequest;
import com.org.example.portfoliobackend.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
