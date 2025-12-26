package com.vinyl.vinyl_management.controller;

import com.vinyl.vinyl_management.dto.GenreDTO;
import com.vinyl.vinyl_management.entity.Genre;
import com.vinyl.vinyl_management.service.GenreService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    
    @Autowired
    private GenreService genreService;
    
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<Genre> genres = genreService.findAll();
        List<GenreDTO> genreDTOs = genres.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(genreDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        Optional<Genre> genre = genreService.findById(id);
        return genre.map(value -> ResponseEntity.ok(convertToDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        try {
            Genre genre = convertToEntity(genreDTO);
            Genre savedGenre = genreService.save(genre);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedGenre));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, 
                                                @RequestBody GenreDTO genreDTO) {
        Optional<Genre> existingGenre = genreService.findById(id);
        if (existingGenre.isPresent()) {
            try {
                Genre genre = updateEntity(genreDTO, existingGenre.get());
                genre.setId(id);
                Genre updatedGenre = genreService.save(genre);
                return ResponseEntity.ok(convertToDTO(updatedGenre));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        if (genreService.findById(id).isPresent()) {
            genreService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<GenreDTO> getGenreByName(@PathVariable String name) {
        Optional<Genre> genre = genreService.findByName(name);
        return genre.map(value -> ResponseEntity.ok(convertToDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    private GenreDTO convertToDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }
    
    private Genre convertToEntity(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        return genre;
    }
    
    private Genre updateEntity(GenreDTO dto, Genre existingGenre) {
        if (dto.getName() != null) {
            existingGenre.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            existingGenre.setDescription(dto.getDescription());
        }
        return existingGenre;
    }
}