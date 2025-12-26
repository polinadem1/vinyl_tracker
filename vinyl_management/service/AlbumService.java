package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.Album;

public interface AlbumService {
    // Базовые CRUD операции
    List<Album> findAll();
    Optional<Album> findById(Long id);
    Album save(Album album);
    void deleteById(Long id);
    
    // Специфичные методы для поиска
    List<Album> findByTitleContaining(String title);
    List<Album> findByArtistId(Long artistId);
    List<Album> findByReleaseYear(Integer year);
    Optional<Album> findByCatalogNumber(String catalogNumber);
}