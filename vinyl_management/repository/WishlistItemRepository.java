package com.vinyl.vinyl_management.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.WishlistItem;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    
    // Поиск элементов wishlist'а пользователя
    List<WishlistItem> findByCollectorId(Long collectorId);
    
    // Поиск элементов по приоритету
    List<WishlistItem> findByCollectorIdAndPriority(Long collectorId, String priority);
    
    // Поиск элементов с высоким приоритетом
    @Query("SELECT wi FROM WishlistItem wi WHERE wi.collector.id = :collectorId AND wi.priority IN ('HIGH', 'URGENT')")
    List<WishlistItem> findHighPriorityItemsByCollectorId(@Param("collectorId") Long collectorId);
    
    // Поиск элементов с ценой ниже определенной
    List<WishlistItem> findByCollectorIdAndMaxPriceLessThanEqual(Long collectorId, Double maxPrice);
    
    // Поиск элементов с ценой в диапазоне
    @Query("SELECT wi FROM WishlistItem wi WHERE wi.collector.id = :collectorId AND wi.maxPrice BETWEEN :minPrice AND :maxPrice")
    List<WishlistItem> findByCollectorIdAndMaxPriceBetween(@Param("collectorId") Long collectorId, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    // Поиск элементов, добавленных после определенной даты
    List<WishlistItem> findByCollectorIdAndAddedAtAfter(Long collectorId, LocalDateTime date);
    
    // Проверка, есть ли уже релиз в wishlist'е пользователя
    boolean existsByCollectorIdAndVinylReleaseId(Long collectorId, Long vinylReleaseId);
    
    // Поиск элементов с заметками
    List<WishlistItem> findByCollectorIdAndNotesIsNotNull(Long collectorId);
    
    // Поиск элементов без заметок
    List<WishlistItem> findByCollectorIdAndNotesIsNull(Long collectorId);
    
    // Статистика: количество элементов в wishlist'е пользователя
    @Query("SELECT COUNT(wi) FROM WishlistItem wi WHERE wi.collector.id = :collectorId")
    Long countByCollectorId(@Param("collectorId") Long collectorId);
    
    // Поиск самых популярных релизов в wishlist'ах (которые хотят многие коллекционеры)
    @Query("SELECT wi.vinylRelease, COUNT(wi) as wishCount FROM WishlistItem wi GROUP BY wi.vinylRelease ORDER BY wishCount DESC")
    List<Object[]> findMostWantedReleases();
}