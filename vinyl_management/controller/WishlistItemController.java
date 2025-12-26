package com.vinyl.vinyl_management.controller;

import com.vinyl.vinyl_management.dto.WishlistItemDTO;
import com.vinyl.vinyl_management.entity.WishlistItem;
import com.vinyl.vinyl_management.entity.VinylRelease;
import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.service.WishlistService;
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
@RequestMapping("/api/wishlist-items")
public class WishlistItemController {
    
    @Autowired
    private WishlistService wishlistService;
    
    @Autowired
    private VinylReleaseService vinylReleaseService;
    
    @Autowired
    private CollectorService collectorService;
    
    @GetMapping
    public ResponseEntity<List<WishlistItemDTO>> getAllWishlistItems() {
        List<WishlistItem> items = wishlistService.findAll();
        List<WishlistItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WishlistItemDTO> getWishlistItemById(@PathVariable Long id) {
        Optional<WishlistItem> item = wishlistService.findById(id);
        return item.map(value -> ResponseEntity.ok(convertToDTO(value)))
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<WishlistItemDTO> createWishlistItem(@RequestBody WishlistItemDTO wishlistItemDTO) {
        try {
            WishlistItem item = convertToEntity(wishlistItemDTO);
            
            // Установка VinylRelease
            if (wishlistItemDTO.getVinylReleaseId() != null) {
                Optional<VinylRelease> vinylRelease = vinylReleaseService.findById(wishlistItemDTO.getVinylReleaseId());
                vinylRelease.ifPresent(item::setVinylRelease);
            }
            
            // Установка Collector
            if (wishlistItemDTO.getCollectorId() != null) {
                Optional<Collector> collector = collectorService.findById(wishlistItemDTO.getCollectorId());
                collector.ifPresent(item::setCollector);
            }
            
            WishlistItem savedItem = wishlistService.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WishlistItemDTO> updateWishlistItem(@PathVariable Long id, 
                                                              @RequestBody WishlistItemDTO wishlistItemDTO) {
        Optional<WishlistItem> existingItem = wishlistService.findById(id);
        if (existingItem.isPresent()) {
            try {
                WishlistItem item = updateEntity(wishlistItemDTO, existingItem.get());
                item.setId(id);
                
                WishlistItem updatedItem = wishlistService.save(item);
                return ResponseEntity.ok(convertToDTO(updatedItem));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long id) {
        if (wishlistService.findById(id).isPresent()) {
            wishlistService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/collector/{collectorId}")
    public ResponseEntity<List<WishlistItemDTO>> getWishlistItemsByCollector(@PathVariable Long collectorId) {
        List<WishlistItem> items = wishlistService.findByCollectorId(collectorId);
        List<WishlistItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    @GetMapping("/collector/{collectorId}/priority/{priority}")
    public ResponseEntity<List<WishlistItemDTO>> getWishlistItemsByCollectorAndPriority(
            @PathVariable Long collectorId, @PathVariable String priority) {
        List<WishlistItem> items = wishlistService.findByCollectorIdAndPriority(collectorId, priority);
        List<WishlistItemDTO> itemDTOs = items.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
    
    private WishlistItemDTO convertToDTO(WishlistItem item) {
        WishlistItemDTO dto = new WishlistItemDTO();
        dto.setId(item.getId());
        dto.setAddedAt(item.getAddedAt());
        dto.setPriority(item.getPriority());
        dto.setMaxPrice(item.getMaxPrice());
        dto.setNotes(item.getNotes());
        
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
    
    private WishlistItem convertToEntity(WishlistItemDTO dto) {
        WishlistItem item = new WishlistItem();
        item.setPriority(dto.getPriority());
        item.setMaxPrice(dto.getMaxPrice());
        item.setNotes(dto.getNotes());
        return item;
    }
    
    private WishlistItem updateEntity(WishlistItemDTO dto, WishlistItem existingItem) {
        if (dto.getPriority() != null) {
            existingItem.setPriority(dto.getPriority());
        }
        if (dto.getMaxPrice() != null) {
            existingItem.setMaxPrice(dto.getMaxPrice());
        }
        if (dto.getNotes() != null) {
            existingItem.setNotes(dto.getNotes());
        }
        return existingItem;
    }
}