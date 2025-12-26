package com.vinyl.vinyl_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.Genre;
import com.vinyl.vinyl_management.repository.GenreRepository;
import com.vinyl.vinyl_management.service.GenreService;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
    
    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }
    
    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
    
    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
    
    @Override
    public Optional<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }
    
    // Дополнительные методы
    public List<Genre> findByNameContaining(String name) {
        return genreRepository.findByNameContainingIgnoreCase(name);
    }
    
    public boolean existsByName(String name) {
        return genreRepository.existsByName(name);
    }
    
    public List<Genre> findGenresWithAlbums() {
        return genreRepository.findGenresWithAlbums();
    }
    
    public Long countAlbumsByGenreId(Long genreId) {
        return genreRepository.countAlbumsByGenreId(genreId);
    }
    
    public List<Object[]> findPopularGenres() {
        return genreRepository.findPopularGenres();
    }
}