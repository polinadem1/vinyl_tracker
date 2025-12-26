package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.WishlistItem;

public interface WishlistService {
    List<WishlistItem> findAll();
    Optional<WishlistItem> findById(Long id);
    WishlistItem save(WishlistItem wishlistItem);
    void deleteById(Long id);
    
    List<WishlistItem> findByCollectorId(Long collectorId);
    List<WishlistItem> findByCollectorIdAndPriority(Long collectorId, String priority);
}