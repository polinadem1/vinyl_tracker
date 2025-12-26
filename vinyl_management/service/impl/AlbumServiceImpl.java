package com.vinyl.vinyl_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.Album;
import com.vinyl.vinyl_management.repository.AlbumRepository;
import com.vinyl.vinyl_management.service.AlbumService;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;
    
    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    
    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }
    
    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }
    
    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
    
    @Override
    public List<Album> findByTitleContaining(String title) {
        return albumRepository.findByTitleContainingIgnoreCase(title);
    }
    
    @Override
    public List<Album> findByArtistId(Long artistId) {
        return albumRepository.findByArtistId(artistId);
    }
    
    @Override
    public List<Album> findByReleaseYear(Integer year) {
        return albumRepository.findByReleaseYear(year);
    }
    
    @Override
    public Optional<Album> findByCatalogNumber(String catalogNumber) {
        return albumRepository.findByCatalogNumber(catalogNumber);
    }
    
    // Дополнительные методы
    public List<Album> findByGenreName(String genreName) {
        return albumRepository.findByGenreName(genreName);
    }
    
    public List<Album> findByReleaseYearRange(Integer startYear, Integer endYear) {
        return albumRepository.findByReleaseYearBetween(startYear, endYear);
    }
    
    public List<Album> findAlbumsWithCoverArt() {
        return albumRepository.findByAlbumArtUrlIsNotNull();
    }
    
    public List<Album> findAlbumsWithoutCoverArt() {
        return albumRepository.findByAlbumArtUrlIsNull();
    }
    
    public List<Album> findByTitleAndArtistName(String title, String artistName) {
        return albumRepository.findByTitleAndArtistName(title, artistName);
    }
}