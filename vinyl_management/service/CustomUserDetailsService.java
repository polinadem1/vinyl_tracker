package com.vinyl.vinyl_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.repository.CollectorRepository;

@Service
public class CustomUserDetailsService {

    @Autowired
    private CollectorRepository collectorRepository;

    public Collector loadUserByEmail(String email) {
        return collectorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + email));
    }
}