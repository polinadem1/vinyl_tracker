package com.vinyl.vinyl_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    
    // Поиск по названию (частичное совпадение, регистронезависимый)
    List<Album> findByTitleContainingIgnoreCase(String title);
    
    // Поиск по точному названию
    Optional<Album> findByTitle(String title);
    
    // Поиск по каталоговому номеру (уникальный)
    Optional<Album> findByCatalogNumber(String catalogNumber);
    
    // Поиск по ID исполнителя
    List<Album> findByArtistId(Long artistId);
    
    // Поиск по году выпуска
    List<Album> findByReleaseYear(Integer releaseYear);
    
    // Поиск альбомов в диапазоне лет
    List<Album> findByReleaseYearBetween(Integer startYear, Integer endYear);
    
    // Поиск по жанру
    @Query("SELECT a FROM Album a JOIN a.genre g WHERE g.id = :genreId")
    List<Album> findByGenreId(@Param("genreId") Long genreId);
    
    // Поиск по названию жанра
    @Query("SELECT a FROM Album a JOIN a.genre g WHERE g.name = :genreName")
    List<Album> findByGenreName(@Param("genreName") String genreName);
    
    // Поиск альбомов с обложкой
    List<Album> findByAlbumArtUrlIsNotNull();
    
    // Поиск альбомов без обложки
    List<Album> findByAlbumArtUrlIsNull();
    
    // Поиск по названию альбома и имени исполнителя
    @Query("SELECT a FROM Album a JOIN a.artist ar WHERE a.title LIKE %:title% AND ar.artistName LIKE %:artistName%")
    List<Album> findByTitleAndArtistName(@Param("title") String title, @Param("artistName") String artistName);
}