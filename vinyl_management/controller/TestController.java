package com.vinyl.vinyl_management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Test endpoint works without Spring Security");
        return response;
    }
    
    @PostMapping("/test-login")
    public Map<String, Object> testLogin(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Login test endpoint works");
        response.put("email", credentials.get("email"));
        response.put("passwordReceived", credentials.get("password"));
        return response;
    }
}