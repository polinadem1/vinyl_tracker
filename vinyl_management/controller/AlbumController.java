package com.vinyl.vinyl_management.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinyl.vinyl_management.dto.AlbumDTO;
import com.vinyl.vinyl_management.entity.Album;
import com.vinyl.vinyl_management.entity.Artist;
import com.vinyl.vinyl_management.entity.Genre;
import com.vinyl.vinyl_management.service.AlbumService;
import com.vinyl.vinyl_management.service.ArtistService;
import com.vinyl.vinyl_management.service.GenreService;

@RestController
@RequestMapping("/api/albums")

public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    @Autowired
    private ArtistService artistService;
    
    @Autowired
    private GenreService genreService;
    
    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<Album> albums = albumService.findAll();
        List<AlbumDTO> albumDTOs = albums.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(albumDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.findById(id);
        return album.map(value -> ResponseEntity.ok(convertToDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        try {
            Album album = convertToEntity(albumDTO);
            
            // Установка артиста
            if (albumDTO.getArtistId() != null) {
                Optional<Artist> artist = artistService.findById(albumDTO.getArtistId());
                artist.ifPresent(album::setArtist);
            }
            
            // Установка жанра
            if (albumDTO.getGenreId() != null) {
                Optional<Genre> genre = genreService.findById(albumDTO.getGenreId());
                genre.ifPresent(album::setGenre);
            }
            
            Album savedAlbum = albumService.save(album);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAlbum));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Long id, 
                                                @RequestBody AlbumDTO albumDTO) {
        Optional<Album> existingAlbum = albumService.findById(id);
        if (existingAlbum.isPresent()) {
            try {
                Album album = updateEntity(albumDTO, existingAlbum.get());
                album.setId(id);
                
                // Обновление артиста
                if (albumDTO.getArtistId() != null) {
                    Optional<Artist> artist = artistService.findById(albumDTO.getArtistId());
                    artist.ifPresent(album::setArtist);
                }
                
                // Обновление жанра
                if (albumDTO.getGenreId() != null) {
                    Optional<Genre> genre = genreService.findById(albumDTO.getGenreId());
                    genre.ifPresent(album::setGenre);
                }
                
                Album updatedAlbum = albumService.save(album);
                return ResponseEntity.ok(convertToDTO(updatedAlbum));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        if (albumService.findById(id).isPresent()) {
            albumService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<AlbumDTO>> searchAlbums(@RequestParam String title) {
        List<Album> albums = albumService.findByTitleContaining(title);
        List<AlbumDTO> albumDTOs = albums.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(albumDTOs);
    }
    
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtist(@PathVariable Long artistId) {
        List<Album> albums = albumService.findByArtistId(artistId);
        List<AlbumDTO> albumDTOs = albums.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(albumDTOs);
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByYear(@PathVariable Integer year) {
        List<Album> albums = albumService.findByReleaseYear(year);
        List<AlbumDTO> albumDTOs = albums.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(albumDTOs);
    }
    
    @GetMapping("/catalog/{catalogNumber}")
    public ResponseEntity<AlbumDTO> getAlbumByCatalogNumber(@PathVariable String catalogNumber) {
        Optional<Album> album = albumService.findByCatalogNumber(catalogNumber);
        return album.map(value -> ResponseEntity.ok(convertToDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    private AlbumDTO convertToDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setReleaseYear(album.getReleaseYear());
        dto.setCatalogNumber(album.getCatalogNumber());
        dto.setAlbumArtUrl(album.getAlbumArtUrl());
        
        if (album.getArtist() != null) {
            dto.setArtistId(album.getArtist().getId());
            dto.setArtistName(album.getArtist().getArtistName());
            dto.setArtistCountry(album.getArtist().getCountry());
        }
        
        if (album.getGenre() != null) {
            dto.setGenreId(album.getGenre().getId());
            dto.setGenreName(album.getGenre().getName());
        }
        
        if (album.getVinylReleases() != null) {
            dto.setVinylReleaseCount(album.getVinylReleases().size());
        }
        
        return dto;
    }
    
    private Album convertToEntity(AlbumDTO dto) {
        Album album = new Album();
        album.setTitle(dto.getTitle());
        album.setReleaseYear(dto.getReleaseYear());
        album.setCatalogNumber(dto.getCatalogNumber());
        album.setAlbumArtUrl(dto.getAlbumArtUrl());
        return album;
    }
    
    private Album updateEntity(AlbumDTO dto, Album existingAlbum) {
        if (dto.getTitle() != null) {
            existingAlbum.setTitle(dto.getTitle());
        }
        if (dto.getReleaseYear() != null) {
            existingAlbum.setReleaseYear(dto.getReleaseYear());
        }
        if (dto.getCatalogNumber() != null) {
            existingAlbum.setCatalogNumber(dto.getCatalogNumber());
        }
        if (dto.getAlbumArtUrl() != null) {
            existingAlbum.setAlbumArtUrl(dto.getAlbumArtUrl());
        }
        return existingAlbum;
    }
}