package com.vinyl.vinyl_management.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "artist_name", nullable = false)
    private String artistName;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "formed_year")
    private Integer formedYear;
    
    @Column(columnDefinition = "TEXT")
    private String biography;
    
    @Column(name = "photo_url")
    private String photoUrl;
    
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>(); 
    
    // Конструкторы
    public Artist() {}
    
    public Artist(String artistName) {
        this.artistName = artistName;
    }
    
    // Конструктор со всеми полями
    public Artist(Long id, String artistName, String country, Integer formedYear, 
                  String biography, String photoUrl, List<Album> albums) {
        this.id = id;
        this.artistName = artistName;
        this.country = country;
        this.formedYear = formedYear;
        this.biography = biography;
        this.photoUrl = photoUrl;
        this.albums = albums;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public Integer getFormedYear() { return formedYear; }
    public void setFormedYear(Integer formedYear) { this.formedYear = formedYear; }
    
    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
    
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    
    public List<Album> getAlbums() { return albums; }
    public void setAlbums(List<Album> albums) { this.albums = albums; }
}