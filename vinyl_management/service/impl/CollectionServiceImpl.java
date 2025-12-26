package com.vinyl.vinyl_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.CollectionItem;
import com.vinyl.vinyl_management.repository.CollectionItemRepository;
import com.vinyl.vinyl_management.service.CollectionService;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {
    
    @Autowired
    private CollectionItemRepository collectionItemRepository;
    
    @Override
    public List<CollectionItem> findAll() {
        return collectionItemRepository.findAll();
    }
    
    @Override
    public Optional<CollectionItem> findById(Long id) {
        return collectionItemRepository.findById(id);
    }
    
    @Override
    public CollectionItem save(CollectionItem collectionItem) {
        return collectionItemRepository.save(collectionItem);
    }
    
    @Override
    public void deleteById(Long id) {
        collectionItemRepository.deleteById(id);
    }
    
    @Override
    public List<CollectionItem> findByCollectorId(Long collectorId) {
        return collectionItemRepository.findByCollectorId(collectorId);
    }
    
    @Override
    public List<CollectionItem> findByVinylReleaseId(Long vinylReleaseId) {
        return collectionItemRepository.findByVinylReleaseId(vinylReleaseId);
    }
    
    @Override
    public List<CollectionItem> findByCollectorIdAndCondition(Long collectorId, String condition) {
        List<CollectionItem> items = collectionItemRepository.findByMediaCondition(condition);
        return items.stream()
                   .filter(item -> item.getCollector().getId().equals(collectorId))
                   .toList();
    }
    
    // Дополнительные методы
    public Long countItemsByCollectorId(Long collectorId) {
        return collectionItemRepository.countByCollectorId(collectorId);
    }
    
    public Double getTotalCollectionValue(Long collectorId) {
        return collectionItemRepository.getTotalCollectionValue(collectorId);
    }
    
    public Double getAverageCollectionValue(Long collectorId) {
        return collectionItemRepository.getAverageCollectionValue(collectorId);
    }
    
    public List<CollectionItem> findByCollectorIdAndSleeveCondition(Long collectorId, String sleeveCondition) {
        List<CollectionItem> items = collectionItemRepository.findBySleeveCondition(sleeveCondition);
        return items.stream()
                   .filter(item -> item.getCollector().getId().equals(collectorId))
                   .toList();
    }
    
    public List<CollectionItem> findByCollectorIdAndValueRange(Long collectorId, Double minValue, Double maxValue) {
        return collectionItemRepository.findByCurrentValueBetween(minValue, maxValue)
                                      .stream()
                                      .filter(item -> item.getCollector().getId().equals(collectorId))
                                      .toList();
    }
    
    public List<CollectionItem> findItemsAddedAfterDate(LocalDateTime date) {
        return collectionItemRepository.findByAddedAtAfter(date);
    }
    
    public List<CollectionItem> findItemsByPurchaseDateRange(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        return collectionItemRepository.findByPurchaseDateBetween(startDate, endDate);
    }
    
    public List<Object[]> findDuplicateReleases() {
        return collectionItemRepository.findDuplicateReleases();
    }
}