package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.VinylRelease;

public interface VinylReleaseService {
    List<VinylRelease> findAll();
    Optional<VinylRelease> findById(Long id);
    VinylRelease save(VinylRelease vinylRelease);
    void deleteById(Long id);
    
    List<VinylRelease> findByAlbumId(Long albumId);
    List<VinylRelease> findByLabel(String label);
    List<VinylRelease> findByReleaseCountry(String country);
}