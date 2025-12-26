package com.vinyl.vinyl_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.VinylRelease;

@Repository
public interface VinylReleaseRepository extends JpaRepository<VinylRelease, Long> {
    
    // Поиск релизов по ID альбома
    List<VinylRelease> findByAlbumId(Long albumId);
    
    // Поиск релизов по лейблу (частичное совпадение)
    List<VinylRelease> findByLabelContainingIgnoreCase(String label);
    
    // Поиск релизов по стране выпуска
    List<VinylRelease> findByReleaseCountry(String country);
    
    // Поиск релизов по стране выпуска (частичное совпадение)
    List<VinylRelease> findByReleaseCountryContainingIgnoreCase(String country);
    
    // Поиск релизов определенного формата
    List<VinylRelease> findByFormat(String format);
    
    // Поиск ограниченных тиражей
    List<VinylRelease> findByLimitedEditionTrue();
    
    // Поиск неограниченных тиражей
    List<VinylRelease> findByLimitedEditionFalse();
    
    // Поиск релизов по цвету винила
    List<VinylRelease> findByColor(String color);
    
    // Поиск релизов по весу
    List<VinylRelease> findByWeight(Integer weight);
    
    // Поиск релизов весом больше определенного значения
    List<VinylRelease> findByWeightGreaterThan(Integer weight);
    
    // Поиск по тиражу
    List<VinylRelease> findByEditionSize(Integer editionSize);
    
    // Поиск релизов с малым тиражом
    @Query("SELECT vr FROM VinylRelease vr WHERE vr.editionSize IS NOT NULL AND vr.editionSize < :maxEditionSize")
    List<VinylRelease> findByEditionSizeLessThan(@Param("maxEditionSize") Integer maxEditionSize);
    
    // Поиск релизов по альбому и стране
    List<VinylRelease> findByAlbumIdAndReleaseCountry(Long albumId, String country);
    
    // Поиск релизов по альбому и формату
    List<VinylRelease> findByAlbumIdAndFormat(Long albumId, String format);
    
    // Статистика: количество релизов по альбому
    @Query("SELECT COUNT(vr) FROM VinylRelease vr WHERE vr.album.id = :albumId")
    Long countByAlbumId(@Param("albumId") Long albumId);
    
    // Поиск уникальных стран выпуска
    @Query("SELECT DISTINCT vr.releaseCountry FROM VinylRelease vr WHERE vr.releaseCountry IS NOT NULL")
    List<String> findDistinctReleaseCountries();
}