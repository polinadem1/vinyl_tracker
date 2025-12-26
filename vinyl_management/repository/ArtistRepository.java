package com.vinyl.vinyl_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    
    // Поиск по имени исполнителя (частичное совпадение, регистронезависимый)
    List<Artist> findByArtistNameContainingIgnoreCase(String artistName);
    
    // Поиск по точному имени исполнителя
    Optional<Artist> findByArtistName(String artistName);
    
    // Поиск по стране
    List<Artist> findByCountry(String country);
    
    // Поиск по стране (частичное совпадение)
    List<Artist> findByCountryContainingIgnoreCase(String country);
    
    // Поиск по году основания
    List<Artist> findByFormedYear(Integer formedYear);
    
    // Поиск исполнителей основанных в диапазоне лет
    List<Artist> findByFormedYearBetween(Integer startYear, Integer endYear);
    
    // Поиск исполнителей с биографией
    List<Artist> findByBiographyIsNotNull();
    
    // Поиск исполнителей без биографии
    List<Artist> findByBiographyIsNull();
    
    // Поиск исполнителей с фото
    List<Artist> findByPhotoUrlIsNotNull();
    
    // Поиск исполнителей с альбомами
    @Query("SELECT DISTINCT a FROM Artist a JOIN a.albums al WHERE al IS NOT NULL")
    List<Artist> findArtistsWithAlbums();
    
    // Подсчет количества альбомов исполнителя
    @Query("SELECT COUNT(al) FROM Album al WHERE al.artist.id = :artistId")
    Long countAlbumsByArtistId(Long artistId);
    
    // Поиск по имени и стране
    List<Artist> findByArtistNameContainingIgnoreCaseAndCountryContainingIgnoreCase(String artistName, String country);
}