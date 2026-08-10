package com.org.example.portfoliobackend.service.impl;

import com.org.example.portfoliobackend.dto.auth.AuthResponse;
import com.org.example.portfoliobackend.dto.auth.LoginRequest;
import com.org.example.portfoliobackend.dto.auth.RegisterRequest;
import com.org.example.portfoliobackend.entity.Role;
import com.org.example.portfoliobackend.entity.User;
import com.org.example.portfoliobackend.exception.BadRequestException;
import com.org.example.portfoliobackend.repository.UserRepository;
import com.org.example.portfoliobackend.security.JwtService;
import com.org.example.portfoliobackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final com.org.example.portfoliobackend.security.CustomUserDetailsService userDetailsService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Publicly registered accounts are always plain USER; admins are seeded/promoted separately.
        user.setRole(Role.USER);
        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(userDetails, Map.of("role", user.getRole().name()));

        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadRequestException("Invalid username or password"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(userDetails, Map.of("role", user.getRole().name()));

        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
