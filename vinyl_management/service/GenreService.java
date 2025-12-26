package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.Genre;

public interface GenreService {
    List<Genre> findAll();
    Optional<Genre> findById(Long id);
    Genre save(Genre genre);
    void deleteById(Long id);
    
    Optional<Genre> findByName(String name);
}