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

import com.vinyl.vinyl_management.dto.CollectorDTO;
import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.service.CollectorService;

@RestController
@RequestMapping("/api/collectors")
public class CollectorController {
    
    @Autowired
    private CollectorService collectorService;
    
    @GetMapping
    public ResponseEntity<List<CollectorDTO>> getAllCollectors() {
        List<Collector> collectors = collectorService.findAll();
        List<CollectorDTO> collectorDTOs = collectors.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(collectorDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CollectorDTO> getCollectorById(@PathVariable Long id) {
        Optional<Collector> collector = collectorService.findById(id);
        return collector.map(value -> ResponseEntity.ok(convertToDTO(value)))
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CollectorDTO> createCollector(@RequestBody CollectorDTO collectorDTO) {
        try {
            Collector collector = convertToEntity(collectorDTO);
            Collector savedCollector = collectorService.save(collector);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedCollector));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CollectorDTO> updateCollector(@PathVariable Long id, 
                                                        @RequestBody CollectorDTO collectorDTO) {
        Optional<Collector> existingCollector = collectorService.findById(id);
        if (existingCollector.isPresent()) {
            try {
                Collector collector = updateEntity(collectorDTO, existingCollector.get());
                collector.setId(id);
                Collector updatedCollector = collectorService.save(collector);
                return ResponseEntity.ok(convertToDTO(updatedCollector));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollector(@PathVariable Long id) {
        if (collectorService.findById(id).isPresent()) {
            collectorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<CollectorDTO> getCollectorByUsername(@PathVariable String username) {
        Optional<Collector> collector = collectorService.findByUsername(username);
        return collector.map(value -> ResponseEntity.ok(convertToDTO(value)))
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<CollectorDTO> getCollectorByEmail(@PathVariable String email) {
        Optional<Collector> collector = collectorService.findByEmail(email);
        return collector.map(value -> ResponseEntity.ok(convertToDTO(value)))
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CollectorDTO>> searchCollectors(@RequestParam String displayName) {
        List<Collector> collectors = collectorService.findByDisplayNameContaining(displayName);
        List<CollectorDTO> collectorDTOs = collectors.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(collectorDTOs);
    }
    
    private CollectorDTO convertToDTO(Collector collector) {
        CollectorDTO dto = new CollectorDTO();
        dto.setId(collector.getId());
        dto.setUsername(collector.getUsername());
        dto.setEmail(collector.getEmail());
        dto.setDisplayName(collector.getDisplayName());
        dto.setAvatarUrl(collector.getAvatarUrl());
        dto.setCreatedAt(collector.getCreatedAt());
        
        if (collector.getCollectionItems() != null) {
            dto.setCollectionItemCount(collector.getCollectionItems().size());
        }
        
        if (collector.getWishlistItems() != null) {
            dto.setWishlistItemCount(collector.getWishlistItems().size());
        }
        
        return dto;
    }
    
    private Collector convertToEntity(CollectorDTO dto) {
        Collector collector = new Collector();
        collector.setUsername(dto.getUsername());
        collector.setEmail(dto.getEmail());
        collector.setPassword("defaultPassword123"); 
        collector.setDisplayName(dto.getDisplayName());
        collector.setAvatarUrl(dto.getAvatarUrl());
        return collector;
    }
    
    private Collector updateEntity(CollectorDTO dto, Collector existingCollector) {
        if (dto.getUsername() != null) {
            existingCollector.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            existingCollector.setEmail(dto.getEmail());
        }
        
        if (dto.getDisplayName() != null) {
            existingCollector.setDisplayName(dto.getDisplayName());
        }
        if (dto.getAvatarUrl() != null) {
            existingCollector.setAvatarUrl(dto.getAvatarUrl());
        }
        return existingCollector;
    }
}