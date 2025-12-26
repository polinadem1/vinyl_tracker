package com.vinyl.vinyl_management.controller;

import com.vinyl.vinyl_management.dto.ArtistDTO;
import com.vinyl.vinyl_management.entity.Artist;
import com.vinyl.vinyl_management.service.ArtistService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    
    @Autowired
    private ArtistService artistService;
    
    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<Artist> artists = artistService.findAll();
        List<ArtistDTO> artistDTOs = artists.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(artistDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id) {
        Optional<Artist> artist = artistService.findById(id);
        return artist.map(value -> ResponseEntity.ok(convertToDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        try {
            Artist artist = convertToEntity(artistDTO);
            Artist savedArtist = artistService.save(artist);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedArtist));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, 
                                                  @RequestBody ArtistDTO artistDTO) {
        Optional<Artist> existingArtist = artistService.findById(id);
        if (existingArtist.isPresent()) {
            try {
                Artist artist = updateEntity(artistDTO, existingArtist.get());
                artist.setId(id);
                Artist updatedArtist = artistService.save(artist);
                return ResponseEntity.ok(convertToDTO(updatedArtist));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        if (artistService.findById(id).isPresent()) {
            artistService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ArtistDTO>> searchArtists(@RequestParam String name) {
        List<Artist> artists = artistService.findByArtistNameContaining(name);
        List<ArtistDTO> artistDTOs = artists.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(artistDTOs);
    }
    
    @GetMapping("/country/{country}")
    public ResponseEntity<List<ArtistDTO>> getArtistsByCountry(@PathVariable String country) {
        List<Artist> artists = artistService.findByCountry(country);
        List<ArtistDTO> artistDTOs = artists.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(artistDTOs);
    }
    
    @GetMapping("/name/{artistName}")
    public ResponseEntity<ArtistDTO> getArtistByName(@PathVariable String artistName) {
        Optional<Artist> artist = artistService.findByArtistName(artistName);
        return artist.map(value -> ResponseEntity.ok(convertToDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    private ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setArtistName(artist.getArtistName());
        dto.setCountry(artist.getCountry());
        dto.setFormedYear(artist.getFormedYear());
        dto.setBiography(artist.getBiography());
        dto.setPhotoUrl(artist.getPhotoUrl());
        
        if (artist.getAlbums() != null) {
            dto.setAlbumCount(artist.getAlbums().size());
        }
        
        return dto;
    }
    
    private Artist convertToEntity(ArtistDTO dto) {
        Artist artist = new Artist();
        artist.setArtistName(dto.getArtistName());
        artist.setCountry(dto.getCountry());
        artist.setFormedYear(dto.getFormedYear());
        artist.setBiography(dto.getBiography());
        artist.setPhotoUrl(dto.getPhotoUrl());
        return artist;
    }
    
    private Artist updateEntity(ArtistDTO dto, Artist existingArtist) {
        if (dto.getArtistName() != null) {
            existingArtist.setArtistName(dto.getArtistName());
        }
        if (dto.getCountry() != null) {
            existingArtist.setCountry(dto.getCountry());
        }
        if (dto.getFormedYear() != null) {
            existingArtist.setFormedYear(dto.getFormedYear());
        }
        if (dto.getBiography() != null) {
            existingArtist.setBiography(dto.getBiography());
        }
        if (dto.getPhotoUrl() != null) {
            existingArtist.setPhotoUrl(dto.getPhotoUrl());
        }
        return existingArtist;
    }
}