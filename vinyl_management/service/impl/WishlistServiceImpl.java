package com.vinyl.vinyl_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.WishlistItem;
import com.vinyl.vinyl_management.repository.WishlistItemRepository;
import com.vinyl.vinyl_management.service.WishlistService;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {
    
    @Autowired
    private WishlistItemRepository wishlistItemRepository;
    
    @Override
    public List<WishlistItem> findAll() {
        return wishlistItemRepository.findAll();
    }
    
    @Override
    public Optional<WishlistItem> findById(Long id) {
        return wishlistItemRepository.findById(id);
    }
    
    @Override
    public WishlistItem save(WishlistItem wishlistItem) {
        return wishlistItemRepository.save(wishlistItem);
    }
    
    @Override
    public void deleteById(Long id) {
        wishlistItemRepository.deleteById(id);
    }
    
    @Override
    public List<WishlistItem> findByCollectorId(Long collectorId) {
        return wishlistItemRepository.findByCollectorId(collectorId);
    }
    
    @Override
    public List<WishlistItem> findByCollectorIdAndPriority(Long collectorId, String priority) {
        return wishlistItemRepository.findByCollectorIdAndPriority(collectorId, priority);
    }
    
    // Дополнительные методы
    public boolean existsByCollectorAndRelease(Long collectorId, Long vinylReleaseId) {
        return wishlistItemRepository.existsByCollectorIdAndVinylReleaseId(collectorId, vinylReleaseId);
    }
    
    public List<WishlistItem> findHighPriorityItemsByCollectorId(Long collectorId) {
        return wishlistItemRepository.findHighPriorityItemsByCollectorId(collectorId);
    }
    
    public List<WishlistItem> findByCollectorIdAndMaxPriceLessThan(Long collectorId, Double maxPrice) {
        return wishlistItemRepository.findByCollectorIdAndMaxPriceLessThanEqual(collectorId, maxPrice);
    }
    
    public List<WishlistItem> findByCollectorIdAndMaxPriceRange(Long collectorId, Double minPrice, Double maxPrice) {
        return wishlistItemRepository.findByCollectorIdAndMaxPriceBetween(collectorId, minPrice, maxPrice);
    }
    
    public Long countByCollectorId(Long collectorId) {
        return wishlistItemRepository.countByCollectorId(collectorId);
    }
    
    public List<WishlistItem> findByCollectorIdWithNotes(Long collectorId) {
        return wishlistItemRepository.findByCollectorIdAndNotesIsNotNull(collectorId);
    }
    
    public List<WishlistItem> findByCollectorIdWithoutNotes(Long collectorId) {
        return wishlistItemRepository.findByCollectorIdAndNotesIsNull(collectorId);
    }
    
    public List<Object[]> findMostWantedReleases() {
        return wishlistItemRepository.findMostWantedReleases();
    }
}