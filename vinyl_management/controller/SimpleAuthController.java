package com.vinyl.vinyl_management.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinyl.vinyl_management.dto.request.LoginRequest;
import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.repository.CollectorRepository;

@RestController
@RequestMapping("/api/simple")
public class SimpleAuthController {

    @Autowired
    private CollectorRepository collectorRepository;

    @PostMapping("/login")
    public ResponseEntity<?> simpleLogin(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("=== SIMPLE LOGIN ATTEMPT ===");
            System.out.println("Email: " + loginRequest.getEmail());
            
            // 1. Находим пользователя
            Collector collector = collectorRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            System.out.println("User found: " + collector.getEmail());
            System.out.println("Stored password hash: " + collector.getPassword());
            
            // 2. Проверяем пароль (пока просто сравниваем строки для демонстрации)

            if (!collector.getPassword().equals(loginRequest.getPassword())) {
                throw new RuntimeException("Invalid password");
            }
            
            // 3. Успешный ответ
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Login successful (simple version)");
            response.put("timestamp", LocalDateTime.now());
            response.put("user", collector.getEmail());
            response.put("role", collector.getRole());
            response.put("note", "This is a simplified version for demonstration");
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            System.out.println("=== LOGIN ERROR: " + e.getMessage() + " ===");
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Login failed: " + e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now());
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}