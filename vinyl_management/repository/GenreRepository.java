package com.vinyl.vinyl_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinyl.vinyl_management.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    
    Optional<Genre> findByName(String name);
    List<Genre> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
    
    @Query("SELECT DISTINCT g FROM Album a JOIN a.genre g")
    List<Genre> findGenresWithAlbums();
    
    @Query("SELECT COUNT(a) FROM Album a WHERE a.genre.id = :genreId")
    Long countAlbumsByGenreId(Long genreId);
    
    @Query("SELECT g.name, COUNT(a) as albumCount FROM Album a JOIN a.genre g GROUP BY g.id, g.name ORDER BY albumCount DESC")
    List<Object[]> findPopularGenres();
}