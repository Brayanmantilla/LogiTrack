package com.logitrack.service;

import com.logitrack.dto.LoginRequest;
import com.logitrack.dto.LoginResponse;
import com.logitrack.dto.RegisterRequest;
import com.logitrack.model.Usuario;
import com.logitrack.model.enums.Rol;  // ← Verificar que esté así
import com.logitrack.repository.UsuarioRepository;
import com.logitrack.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    
    public LoginResponse login(LoginRequest request) {
        log.info("Intento de login para: {}", request.getEmail());
        
        // Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Verificar que el usuario esté activo
        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }
        
        // VALIDAR CONTRASEÑA
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            log.error("Contraseña incorrecta para: {}", request.getEmail());
            throw new RuntimeException("Contraseña incorrecta");
        }
        
        log.info("Login exitoso para: {}", request.getEmail());
        
        // Generar token JWT
        String token = tokenProvider.generateToken(usuario.getEmail());
        
        // Retornar respuesta
        return LoginResponse.builder()
            .token(token)
            .tipo("Bearer")
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .email(usuario.getEmail())
            .rol(usuario.getRol().name())
            .build();
    }
    
    public LoginResponse register(RegisterRequest request) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol() != null ? request.getRol() : Rol.EMPLEADO);
        usuario.setActivo(true);
        
        // Guardar en BD
        usuario = usuarioRepository.save(usuario);
        
        log.info("Usuario registrado: {}", usuario.getEmail());
        
        // Generar token
        String token = tokenProvider.generateToken(usuario.getEmail());
        
        // Retornar respuesta
        return LoginResponse.builder()
            .token(token)
            .tipo("Bearer")
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .email(usuario.getEmail())
            .rol(usuario.getRol().name())
            .build();
    }
}