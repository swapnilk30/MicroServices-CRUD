package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    public LoginResponse login(LoginRequest request) {

        log.info("Login request received for email: {}", request.getEmail());
        // Implement your login logic here
        return LoginResponse.builder()
                .token("dummy-token")
                .email(request.getEmail())
                .build();
    }


}
