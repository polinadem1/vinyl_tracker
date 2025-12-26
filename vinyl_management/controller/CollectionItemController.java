package com.vinyl.vinyl_management.controller;

import com.vinyl.vinyl_management.dto.CollectionItemDTO;
import com.vinyl.vinyl_management.entity.CollectionItem;
import com.vinyl.vinyl_management.entity.VinylRelease;
import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.service.CollectionService;
import com.vinyl.vinyl_management.service.VinylReleaseService;
import com.vinyl.vinyl_management.service.CollectorService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/collection-items")
public class CollectionItemController {
    
    @Autowired
    private CollectionService collectionService;
    
    @Autowired
    private VinylReleaseService vinylReleaseService;
    
    @Autowired
    private CollectorService collectorService;
    
    @GetMapping
    public ResponseEntity<List<CollectionItemDTO>> getAllCollectionItems() {
        List<CollectionItem> items = collectionService.findAll();
        List<CollectionItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CollectionItemDTO> getCollectionItemById(@PathVariable Long id) {
        Optional<CollectionItem> item = collectionService.findById(id);
        return item.map(value -> ResponseEntity.ok(convertToDTO(value)))
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CollectionItemDTO> createCollectionItem(@RequestBody CollectionItemDTO collectionItemDTO) {
        try {
            CollectionItem item = convertToEntity(collectionItemDTO);
            
            // Установка VinylRelease
            if (collectionItemDTO.getVinylReleaseId() != null) {
                Optional<VinylRelease> vinylRelease = vinylReleaseService.findById(collectionItemDTO.getVinylReleaseId());
                vinylRelease.ifPresent(item::setVinylRelease);
            }
            
            // Установка Collector
            if (collectionItemDTO.getCollectorId() != null) {
                Optional<Collector> collector = collectorService.findById(collectionItemDTO.getCollectorId());
                collector.ifPresent(item::setCollector);
            }
            
            CollectionItem savedItem = collectionService.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CollectionItemDTO> updateCollectionItem(@PathVariable Long id, 
                                                                  @RequestBody CollectionItemDTO collectionItemDTO) {
        Optional<CollectionItem> existingItem = collectionService.findById(id);
        if (existingItem.isPresent()) {
            try {
                CollectionItem item = updateEntity(collectionItemDTO, existingItem.get());
                item.setId(id);
                
                CollectionItem updatedItem = collectionService.save(item);
                return ResponseEntity.ok(convertToDTO(updatedItem));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionItem(@PathVariable Long id) {
        if (collectionService.findById(id).isPresent()) {
            collectionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/collector/{collectorId}")
    public ResponseEntity<List<CollectionItemDTO>> getCollectionItemsByCollector(@PathVariable Long collectorId) {
        List<CollectionItem> items = collectionService.findByCollectorId(collectorId);
        List<CollectionItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    @GetMapping("/vinyl-release/{vinylReleaseId}")
    public ResponseEntity<List<CollectionItemDTO>> getCollectionItemsByVinylRelease(@PathVariable Long vinylReleaseId) {
        List<CollectionItem> items = collectionService.findByVinylReleaseId(vinylReleaseId);
        List<CollectionItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    @GetMapping("/collector/{collectorId}/condition/{condition}")
    public ResponseEntity<List<CollectionItemDTO>> getCollectionItemsByCollectorAndCondition(
            @PathVariable Long collectorId, @PathVariable String condition) {
        List<CollectionItem> items = collectionService.findByCollectorIdAndCondition(collectorId, condition);
        List<CollectionItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    private CollectionItemDTO convertToDTO(CollectionItem item) {
        CollectionItemDTO dto = new CollectionItemDTO();
        dto.setId(item.getId());
        dto.setPurchaseDate(item.getPurchaseDate());
        dto.setPurchasePrice(item.getPurchasePrice());
        dto.setCurrentValue(item.getCurrentValue());
        dto.setMediaCondition(item.getMediaCondition());
        dto.setSleeveCondition(item.getSleeveCondition());
        dto.setNotes(item.getNotes());
        dto.setAddedAt(item.getAddedAt());
        
        if (item.getVinylRelease() != null) {
            dto.setVinylReleaseId(item.getVinylRelease().getId());
            
            if (item.getVinylRelease().getAlbum() != null) {
                dto.setAlbumTitle(item.getVinylRelease().getAlbum().getTitle());
                
                if (item.getVinylRelease().getAlbum().getArtist() != null) {
                    dto.setArtistName(item.getVinylRelease().getAlbum().getArtist().getArtistName());
                }
            }
        }
        
        if (item.getCollector() != null) {
            dto.setCollectorId(item.getCollector().getId());
            dto.setCollectorUsername(item.getCollector().getUsername());
        }
        
        return dto;
    }
    
    private CollectionItem convertToEntity(CollectionItemDTO dto) {
        CollectionItem item = new CollectionItem();
        item.setPurchaseDate(dto.getPurchaseDate());
        item.setPurchasePrice(dto.getPurchasePrice());
        item.setCurrentValue(dto.getCurrentValue());
        item.setMediaCondition(dto.getMediaCondition());
        item.setSleeveCondition(dto.getSleeveCondition());
        item.setNotes(dto.getNotes());
        return item;
    }
    
    private CollectionItem updateEntity(CollectionItemDTO dto, CollectionItem existingItem) {
        if (dto.getPurchaseDate() != null) {
            existingItem.setPurchaseDate(dto.getPurchaseDate());
        }
        if (dto.getPurchasePrice() != null) {
            existingItem.setPurchasePrice(dto.getPurchasePrice());
        }
        if (dto.getCurrentValue() != null) {
            existingItem.setCurrentValue(dto.getCurrentValue());
        }
        if (dto.getMediaCondition() != null) {
            existingItem.setMediaCondition(dto.getMediaCondition());
        }
        if (dto.getSleeveCondition() != null) {
            existingItem.setSleeveCondition(dto.getSleeveCondition());
        }
        if (dto.getNotes() != null) {
            existingItem.setNotes(dto.getNotes());
        }
        return existingItem;
    }
}