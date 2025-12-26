package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.Collector;

public interface CollectorService {
    List<Collector> findAll();
    Optional<Collector> findById(Long id);
    Collector save(Collector collector);
    void deleteById(Long id);
    
    Optional<Collector> findByUsername(String username);
    Optional<Collector> findByEmail(String email);
    List<Collector> findByDisplayNameContaining(String displayName);
}