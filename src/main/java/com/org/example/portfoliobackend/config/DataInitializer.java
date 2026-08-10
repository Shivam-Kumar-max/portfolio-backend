package com.org.example.portfoliobackend.config;

import com.org.example.portfoliobackend.entity.Role;
import com.org.example.portfoliobackend.entity.User;
import com.org.example.portfoliobackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds a default admin account on startup so there is always at least one
 * admin user available to manage the portfolio content. Override the
 * credentials via application.properties (admin.default-username etc.)
 * before deploying anywhere public, and change the password after first login.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.default-username}")
    private String defaultUsername;

    @Value("${admin.default-email}")
    private String defaultEmail;

    @Value("${admin.default-password}")
    private String defaultPassword;

    @Override
    public void run(String... args) {
        if (userRepository.existsByUsername(defaultUsername)) {
            return;
        }
        User admin = new User();
        admin.setUsername(defaultUsername);
        admin.setEmail(defaultEmail);
        admin.setPassword(passwordEncoder.encode(defaultPassword));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
        System.out.println("Seeded default admin user '" + defaultUsername + "'. Change the password after first login.");
    }
}
