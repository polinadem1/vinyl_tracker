package com.vinyl.vinyl_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.Collector;  

@Repository
public interface CollectorRepository extends JpaRepository<Collector, Long> {  

    // Поиск по имени пользователя
    Optional<Collector> findByUsername(String username);
    
    // Поиск по email
    Optional<Collector> findByEmail(String email);
    
    // Поиск по отображаемому имени (частичное совпадение)
    List<Collector> findByDisplayNameContainingIgnoreCase(String displayName);
    
    // Проверка существования по email
    boolean existsByEmail(String email);
    
    // Проверка существования по имени пользователя
    boolean existsByUsername(String username);
    
    // Поиск по наличию аватара
    List<Collector> findByAvatarUrlIsNotNull();
    
    // Поиск по отсутствию аватара
    List<Collector> findByAvatarUrlIsNull();

    // Поиск коллекционеров с элементами коллекции
    @Query("SELECT DISTINCT c FROM Collector c JOIN c.collectionItems ci")
    List<Collector> findCollectorsWithCollection();
    
    // Поиск коллекционеров с вишлистами
    @Query("SELECT DISTINCT c FROM Collector c JOIN c.wishlistItems wi")
    List<Collector> findCollectorsWithWishlist();
    
    // Поиск по отображаемому имени ИЛИ имени пользователя
    @Query("SELECT c FROM Collector c WHERE LOWER(c.displayName) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.username) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Collector> findByDisplayNameOrUsernameContainingIgnoreCase(String search);
}