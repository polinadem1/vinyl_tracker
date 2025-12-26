package com.vinyl.vinyl_management.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinyl.vinyl_management.dto.CollectorDTO;
import com.vinyl.vinyl_management.dto.request.LoginRequest;
import com.vinyl.vinyl_management.dto.request.RegisterRequestDTO;
import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.repository.CollectorRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private CollectorRepository collectorRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // РЕГИСТРАЦИЯ
    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequest) {
    try {
        System.out.println("=== AUTH REGISTER ATTEMPT ===");
        System.out.println("Email: " + registerRequest.getEmail());
        System.out.println("Username: " + registerRequest.getUsername());
        
        // 1. Проверяем, существует ли пользователь с таким email
        if (collectorRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Email already exists");
            errorResponse.put("timestamp", LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        
        // 2. Проверяем, существует ли пользователь с таким username
        if (collectorRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Username already exists");
            errorResponse.put("timestamp", LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        
        // 3. Создаем нового пользователя
        Collector collector = new Collector();
        collector.setUsername(registerRequest.getUsername());
        collector.setEmail(registerRequest.getEmail());
        
        // 4. Хешируем пароль
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        collector.setPassword(encodedPassword);
        
        // 5. Устанавливаем displayName (если не задан, используем username)
        if (registerRequest.getDisplayName() != null && !registerRequest.getDisplayName().trim().isEmpty()) {
            collector.setDisplayName(registerRequest.getDisplayName());
        } else {
            collector.setDisplayName(registerRequest.getUsername());
        }
        
        // 6. Устанавливаем avatarUrl если есть
        if (registerRequest.getAvatarUrl() != null && !registerRequest.getAvatarUrl().trim().isEmpty()) {
            collector.setAvatarUrl(registerRequest.getAvatarUrl());
        }
        
        // 7. Устанавливаем дату создания
        collector.setCreatedAt(LocalDateTime.now());
        
        
        // 9. Сохраняем в базу
        Collector savedCollector = collectorRepository.save(collector);
        
        System.out.println("User registered successfully: " + savedCollector.getEmail());
        System.out.println("User ID: " + savedCollector.getId());
        
        // 10. Создаем DTO для ответа
        CollectorDTO collectorDTO = new CollectorDTO();
        collectorDTO.setId(savedCollector.getId());
        collectorDTO.setUsername(savedCollector.getUsername());
        collectorDTO.setEmail(savedCollector.getEmail());
        collectorDTO.setDisplayName(savedCollector.getDisplayName());
        collectorDTO.setAvatarUrl(savedCollector.getAvatarUrl());
        collectorDTO.setCreatedAt(savedCollector.getCreatedAt());
        
        // 11. Успешный ответ
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Registration successful");
        response.put("timestamp", LocalDateTime.now());
        response.put("user", collectorDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
    } catch (Exception e) {
        System.out.println("=== REGISTRATION ERROR: " + e.getMessage() + " ===");
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", "Registration failed: " + e.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

    //ЛОГИН
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("=== AUTH LOGIN ATTEMPT ===");
            
            // 1. Находим пользователя
            Collector collector = collectorRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            // 2. Проверяем пароль с BCrypt
            boolean passwordValid = passwordEncoder.matches(
                loginRequest.getPassword(),
                collector.getPassword()
            );
            
            System.out.println("Password valid: " + passwordValid);
            
            if (!passwordValid) {
                throw new RuntimeException("Invalid password");
            }
            
            // 3. Создаем DTO для ответа
            CollectorDTO collectorDTO = new CollectorDTO();
            collectorDTO.setId(collector.getId());
            collectorDTO.setUsername(collector.getUsername());
            collectorDTO.setEmail(collector.getEmail());
            collectorDTO.setDisplayName(collector.getDisplayName());
            collectorDTO.setAvatarUrl(collector.getAvatarUrl());
            collectorDTO.setCreatedAt(collector.getCreatedAt());
            collectorDTO.setUpdatedAt(collector.getCreatedAt());
            
            // 4. Успешный ответ
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("timestamp", LocalDateTime.now());
            response.put("user", collectorDTO);
            response.put("role", collector.getRole());
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            System.out.println("=== LOGIN ERROR: " + e.getMessage() + " ===");
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Authentication failed: " + e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now());
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
    
    //ТЕСТОВЫЙ ЭНДПОИНТ
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth controller is working!");
    }
}