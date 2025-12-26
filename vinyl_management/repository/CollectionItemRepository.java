package com.vinyl.vinyl_management.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.CollectionItem;

@Repository
public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
    
    // Поиск элементов коллекции пользователя
    List<CollectionItem> findByCollectorId(Long collectorId);
    
    // Поиск элементов по ID релиза винила
    List<CollectionItem> findByVinylReleaseId(Long vinylReleaseId);
    
    // Поиск элементов по состоянию пластинки
    List<CollectionItem> findByMediaCondition(String condition);
    
    // Поиск элементов по состоянию конверта
    List<CollectionItem> findBySleeveCondition(String condition);
    
    // Поиск элементов с определенным состоянием пластинки и конверта
    List<CollectionItem> findByMediaConditionAndSleeveCondition(String mediaCondition, String sleeveCondition);
    
    // Поиск элементов, добавленных после определенной даты
    List<CollectionItem> findByAddedAtAfter(LocalDateTime date);
    
    // Поиск элементов, добавленных до определенной даты
    List<CollectionItem> findByAddedAtBefore(LocalDateTime date);
    
    // Поиск элементов по диапазону даты покупки
    List<CollectionItem> findByPurchaseDateBetween(java.time.LocalDate startDate, java.time.LocalDate endDate);
    
    // Поиск элементов с ценой в диапазоне
    @Query("SELECT ci FROM CollectionItem ci WHERE ci.currentValue BETWEEN :minPrice AND :maxPrice")
    List<CollectionItem> findByCurrentValueBetween(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    // Статистика: количество пластинок в коллекции пользователя
    @Query("SELECT COUNT(ci) FROM CollectionItem ci WHERE ci.collector.id = :collectorId")
    Long countByCollectorId(@Param("collectorId") Long collectorId);
    
    // Общая стоимость коллекции пользователя
    @Query("SELECT COALESCE(SUM(ci.currentValue), 0) FROM CollectionItem ci WHERE ci.collector.id = :collectorId")
    Double getTotalCollectionValue(@Param("collectorId") Long collectorId);
    
    // Средняя стоимость пластинки в коллекции пользователя
    @Query("SELECT COALESCE(AVG(ci.currentValue), 0) FROM CollectionItem ci WHERE ci.collector.id = :collectorId")
    Double getAverageCollectionValue(@Param("collectorId") Long collectorId);
    
    // Найти дубликаты (несколько пользователей имеют один и тот же релиз)
    @Query("SELECT ci.vinylRelease FROM CollectionItem ci GROUP BY ci.vinylRelease HAVING COUNT(ci) > 1")
    List<Object[]> findDuplicateReleases();

    List<CollectionItem> findByCollectorIdAndSleeveCondition(Long collectorId, String sleeveCondition);
}