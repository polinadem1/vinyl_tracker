package com.vinyl.vinyl_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.Artist;
import com.vinyl.vinyl_management.repository.ArtistRepository;
import com.vinyl.vinyl_management.service.ArtistService;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {
    
    @Autowired
    private ArtistRepository artistRepository;
    
    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
    
    @Override
    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }
    
    @Override
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }
    
    @Override
    public void deleteById(Long id) {
        artistRepository.deleteById(id);
    }
    
    @Override
    public List<Artist> findByArtistNameContaining(String name) {
        return artistRepository.findByArtistNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<Artist> findByCountry(String country) {
        return artistRepository.findByCountry(country);
    }
    
    @Override
    public Optional<Artist> findByArtistName(String artistName) {
        return artistRepository.findByArtistName(artistName);
    }
    
    // Дополнительные методы
    public List<Artist> findArtistsWithBiography() {
        return artistRepository.findByBiographyIsNotNull();
    }
    
    public List<Artist> findArtistsWithoutBiography() {
        return artistRepository.findByBiographyIsNull();
    }
    
    public List<Artist> findArtistsWithAlbums() {
        return artistRepository.findArtistsWithAlbums();
    }
    
    public Long countAlbumsByArtistId(Long artistId) {
        return artistRepository.countAlbumsByArtistId(artistId);
    }
    
    public List<Artist> findByFormedYearRange(Integer startYear, Integer endYear) {
        return artistRepository.findByFormedYearBetween(startYear, endYear);
    }
    
    public List<Artist> findByArtistNameAndCountry(String artistName, String country) {
        return artistRepository.findByArtistNameContainingIgnoreCaseAndCountryContainingIgnoreCase(artistName, country);
    }
}