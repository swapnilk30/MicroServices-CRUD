package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.UserAlreadyExistsException;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.security.CustomUserDetails;
import com.example.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        log.info("Register request received for email: {}", request.getEmail());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Registration failed. Email already exists: {}", request.getEmail());
            throw new UserAlreadyExistsException(
                    "User already exists with email : " + request.getEmail());
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        // Encrypt Password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Default Role
        user.setRole("ROLE_USER");

        userRepository.save(user);

        log.info("User registered successfully with email: {}", request.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        log.info("Login request received for email: {}", request.getEmail());

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    ));
            CustomUserDetails userDetails =
                    (CustomUserDetails) authentication.getPrincipal();

            log.info("Authentication successful for {}", userDetails.getUsername());

            String token = jwtService.generateToken(userDetails);

            log.info("JWT token generated successfully for {}", userDetails.getUsername());

            return LoginResponse.builder()
                    .token(token)
                    .tokenType("Bearer")
                    .email(userDetails.getUsername())
                    .build();

        }catch (BadCredentialsException e){
            log.warn("Invalid login attempt for {}", request.getEmail());
            throw new InvalidCredentialsException(
                    "Invalid email or password");
        }
    }


}
