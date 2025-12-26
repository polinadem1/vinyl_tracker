package com.vinyl.vinyl_management.controller;

import com.vinyl.vinyl_management.dto.VinylReleaseDTO;
import com.vinyl.vinyl_management.entity.VinylRelease;
import com.vinyl.vinyl_management.entity.Album;
import com.vinyl.vinyl_management.service.VinylReleaseService;
import com.vinyl.vinyl_management.service.AlbumService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vinyl-releases")
public class VinylReleaseController {
    
    @Autowired
    private VinylReleaseService vinylReleaseService;
    
    @Autowired
    private AlbumService albumService;
    
    @GetMapping
    public ResponseEntity<List<VinylReleaseDTO>> getAllVinylReleases() {
        List<VinylRelease> releases = vinylReleaseService.findAll();
        List<VinylReleaseDTO> releaseDTOs = releases.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(releaseDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VinylReleaseDTO> getVinylReleaseById(@PathVariable Long id) {
        Optional<VinylRelease> release = vinylReleaseService.findById(id);
        return release.map(value -> ResponseEntity.ok(convertToDTO(value)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<VinylReleaseDTO> createVinylRelease(@RequestBody VinylReleaseDTO vinylReleaseDTO) {
        try {
            VinylRelease release = convertToEntity(vinylReleaseDTO);
            
            // Установка альбома
            if (vinylReleaseDTO.getAlbumId() != null) {
                Optional<Album> album = albumService.findById(vinylReleaseDTO.getAlbumId());
                album.ifPresent(release::setAlbum);
            }
            
            VinylRelease savedRelease = vinylReleaseService.save(release);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedRelease));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VinylReleaseDTO> updateVinylRelease(@PathVariable Long id, 
                                                              @RequestBody VinylReleaseDTO vinylReleaseDTO) {
        Optional<VinylRelease> existingRelease = vinylReleaseService.findById(id);
        if (existingRelease.isPresent()) {
            try {
                VinylRelease release = updateEntity(vinylReleaseDTO, existingRelease.get());
                release.setId(id);
                VinylRelease updatedRelease = vinylReleaseService.save(release);
                return ResponseEntity.ok(convertToDTO(updatedRelease));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVinylRelease(@PathVariable Long id) {
        if (vinylReleaseService.findById(id).isPresent()) {
            vinylReleaseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<VinylReleaseDTO>> getVinylReleasesByAlbum(@PathVariable Long albumId) {
        List<VinylRelease> releases = vinylReleaseService.findByAlbumId(albumId);
        List<VinylReleaseDTO> releaseDTOs = releases.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(releaseDTOs);
    }
    
    @GetMapping("/label/{label}")
    public ResponseEntity<List<VinylReleaseDTO>> getVinylReleasesByLabel(@PathVariable String label) {
        List<VinylRelease> releases = vinylReleaseService.findByLabel(label);
        List<VinylReleaseDTO> releaseDTOs = releases.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(releaseDTOs);
    }
    
    @GetMapping("/country/{country}")
    public ResponseEntity<List<VinylReleaseDTO>> getVinylReleasesByCountry(@PathVariable String country) {
        List<VinylRelease> releases = vinylReleaseService.findByReleaseCountry(country);
        List<VinylReleaseDTO> releaseDTOs = releases.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(releaseDTOs);
    }
    
    private VinylReleaseDTO convertToDTO(VinylRelease release) {
        VinylReleaseDTO dto = new VinylReleaseDTO();
        dto.setId(release.getId());
        dto.setReleaseCountry(release.getReleaseCountry());
        dto.setLabel(release.getLabel());
        dto.setFormat(release.getFormat());
        dto.setColor(release.getColor());
        dto.setWeight(release.getWeight());
        dto.setLimitedEdition(release.getLimitedEdition());
        dto.setEditionSize(release.getEditionSize());
        
        if (release.getAlbum() != null) {
            dto.setAlbumId(release.getAlbum().getId());
            dto.setAlbumTitle(release.getAlbum().getTitle());
            dto.setAlbumCatalogNumber(release.getAlbum().getCatalogNumber());
            
            if (release.getAlbum().getArtist() != null) {
                dto.setArtistName(release.getAlbum().getArtist().getArtistName());
            }
        }
        
        return dto;
    }
    
    private VinylRelease convertToEntity(VinylReleaseDTO dto) {
        VinylRelease release = new VinylRelease();
        release.setReleaseCountry(dto.getReleaseCountry());
        release.setLabel(dto.getLabel());
        release.setFormat(dto.getFormat());
        release.setColor(dto.getColor());
        release.setWeight(dto.getWeight());
        release.setLimitedEdition(dto.getLimitedEdition());
        release.setEditionSize(dto.getEditionSize());
        return release;
    }
    
    private VinylRelease updateEntity(VinylReleaseDTO dto, VinylRelease existingRelease) {
        if (dto.getReleaseCountry() != null) {
            existingRelease.setReleaseCountry(dto.getReleaseCountry());
        }
        if (dto.getLabel() != null) {
            existingRelease.setLabel(dto.getLabel());
        }
        if (dto.getFormat() != null) {
            existingRelease.setFormat(dto.getFormat());
        }
        if (dto.getColor() != null) {
            existingRelease.setColor(dto.getColor());
        }
        if (dto.getWeight() != null) {
            existingRelease.setWeight(dto.getWeight());
        }
        if (dto.getLimitedEdition() != null) {
            existingRelease.setLimitedEdition(dto.getLimitedEdition());
        }
        if (dto.getEditionSize() != null) {
            existingRelease.setEditionSize(dto.getEditionSize());
        }
        return existingRelease;
    }
}