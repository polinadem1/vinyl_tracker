package com.vinyl.vinyl_management.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "release_year")
    private Integer releaseYear;
    
    @Column(name = "catalog_number", unique = true)
    private String catalogNumber;
    
    @Column(name = "album_art_url")
    private String albumArtUrl;
    
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    
    @ManyToOne  
    @JoinColumn(name = "genre_id")
    private Genre genre;
    
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<VinylRelease> vinylReleases = new ArrayList<>();
    
    // Конструкторы
    public Album() {}
    
    public Album(String title, Integer releaseYear, String catalogNumber) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.catalogNumber = catalogNumber;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }
    
    public String getCatalogNumber() { return catalogNumber; }
    public void setCatalogNumber(String catalogNumber) { this.catalogNumber = catalogNumber; }
    
    public String getAlbumArtUrl() { return albumArtUrl; }
    public void setAlbumArtUrl(String albumArtUrl) { this.albumArtUrl = albumArtUrl; }
    
    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }
    

    public Genre getGenre() { 
        return genre; 
    }
    
    public void setGenre(Genre genre) { 
        this.genre = genre;  
    }
    
    public List<VinylRelease> getVinylReleases() { 
        return vinylReleases; 
    }
    
    public void setVinylReleases(List<VinylRelease> vinylReleases) { 
        this.vinylReleases = vinylReleases; 
    }
}
