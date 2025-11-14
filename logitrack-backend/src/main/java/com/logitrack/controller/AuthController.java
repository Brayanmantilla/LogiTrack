package com.logitrack.controller;

import com.logitrack.dto.LoginRequest;
import com.logitrack.dto.LoginResponse;
import com.logitrack.dto.RegisterRequest;
import com.logitrack.model.Usuario;
import com.logitrack.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@Valid @RequestBody RegisterRequest request) {
        Usuario usuario = authService.register(request);
        return ResponseEntity.ok(usuario);
    }
}