package com.vinyl.vinyl_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.VinylRelease;
import com.vinyl.vinyl_management.repository.VinylReleaseRepository;
import com.vinyl.vinyl_management.service.VinylReleaseService;

@Service
@Transactional
public class VinylReleaseServiceImpl implements VinylReleaseService {
    
    @Autowired
    private VinylReleaseRepository vinylReleaseRepository;
    
    @Override
    public List<VinylRelease> findAll() {
        return vinylReleaseRepository.findAll();
    }
    
    @Override
    public Optional<VinylRelease> findById(Long id) {
        return vinylReleaseRepository.findById(id);
    }
    
    @Override
    public VinylRelease save(VinylRelease vinylRelease) {
        return vinylReleaseRepository.save(vinylRelease);
    }
    
    @Override
    public void deleteById(Long id) {
        vinylReleaseRepository.deleteById(id);
    }
    
    @Override
    public List<VinylRelease> findByAlbumId(Long albumId) {
        return vinylReleaseRepository.findByAlbumId(albumId);
    }
    
    @Override
    public List<VinylRelease> findByLabel(String label) {
        return vinylReleaseRepository.findByLabelContainingIgnoreCase(label);
    }
    
    @Override
    public List<VinylRelease> findByReleaseCountry(String country) {
        return vinylReleaseRepository.findByReleaseCountry(country);
    }
    
    // Дополнительные методы
    public List<VinylRelease> findByFormat(String format) {
        return vinylReleaseRepository.findByFormat(format);
    }
    
    public List<VinylRelease> findLimitedEditions() {
        return vinylReleaseRepository.findByLimitedEditionTrue();
    }
    
    public List<VinylRelease> findByColor(String color) {
        return vinylReleaseRepository.findByColor(color);
    }
    
    public List<VinylRelease> findByWeightGreaterThan(Integer weight) {
        return vinylReleaseRepository.findByWeightGreaterThan(weight);
    }
    
    public Long countByAlbumId(Long albumId) {
        return vinylReleaseRepository.countByAlbumId(albumId);
    }
    
    public List<VinylRelease> findByAlbumIdAndCountry(Long albumId, String country) {
        return vinylReleaseRepository.findByAlbumIdAndReleaseCountry(albumId, country);
    }
    
    public List<VinylRelease> findByAlbumIdAndFormat(Long albumId, String format) {
        return vinylReleaseRepository.findByAlbumIdAndFormat(albumId, format);
    }
    
    public List<VinylRelease> findByEditionSizeLessThan(Integer maxEditionSize) {
        return vinylReleaseRepository.findByEditionSizeLessThan(maxEditionSize);
    }
    
    public List<String> findDistinctReleaseCountries() {
        return vinylReleaseRepository.findDistinctReleaseCountries();
    }
}