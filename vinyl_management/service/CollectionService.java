package com.vinyl.vinyl_management.service;

import java.util.List;
import java.util.Optional;

import com.vinyl.vinyl_management.entity.CollectionItem;

public interface CollectionService {
    List<CollectionItem> findAll();
    Optional<CollectionItem> findById(Long id);
    CollectionItem save(CollectionItem collectionItem);
    void deleteById(Long id);
    
    List<CollectionItem> findByCollectorId(Long collectorId);
    List<CollectionItem> findByVinylReleaseId(Long vinylReleaseId);
    List<CollectionItem> findByCollectorIdAndCondition(Long collectorId, String condition);
}