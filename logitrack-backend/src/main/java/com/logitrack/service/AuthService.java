package com.logitrack.service;

import com.logitrack.dto.LoginResponse;
import com.logitrack.dto.LoginRequest;
import com.logitrack.dto.RegisterRequest;
import com.logitrack.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final JwtTokenProvider tokenProvider;
    
    public LoginResponse login(LoginRequest request) {
        log.info("Intento de login para: {}", request.getEmail());
        
        String token = tokenProvider.generateToken(request.getEmail());
        
        return LoginResponse.builder()
            .token(token)
            .tipo("Bearer")
            .id(1L)
            .nombre("Usuario Temporal")
            .email(request.getEmail())
            .rol("ADMIN")
            .build();
    }
    
    public LoginResponse register(RegisterRequest request) {
        String token = tokenProvider.generateToken(request.getEmail());
        
        return LoginResponse.builder()
            .token(token)
            .tipo("Bearer")
            .id(1L)
            .nombre(request.getNombre())
            .email(request.getEmail())
            .rol("EMPLEADO")
            .build();
    }
}