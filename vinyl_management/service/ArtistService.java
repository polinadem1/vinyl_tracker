package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.Artist;

public interface ArtistService {
    List<Artist> findAll();
    Optional<Artist> findById(Long id);
    Artist save(Artist artist);
    void deleteById(Long id);
    
    List<Artist> findByArtistNameContaining(String name);
    List<Artist> findByCountry(String country);
    Optional<Artist> findByArtistName(String artistName);
}