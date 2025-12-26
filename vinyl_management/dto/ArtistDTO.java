package com.vinyl.vinyl_management.dto;

public class ArtistDTO {
    private Long id;
    private String artistName;
    private String country;
    private Integer formedYear;
    private String biography;
    private String photoUrl;
    

    private Integer albumCount;
    
    // Конструкторы
    public ArtistDTO() {}
    
    public ArtistDTO(Long id, String artistName, String country, Integer formedYear, 
                    String biography, String photoUrl) {
        this.id = id;
        this.artistName = artistName;
        this.country = country;
        this.formedYear = formedYear;
        this.biography = biography;
        this.photoUrl = photoUrl;
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

    // public List<AlbumDTO> getAlbums() { return albums; }
    // public void setAlbums(List<AlbumDTO> albums) { this.albums = albums; }
    
    public Integer getAlbumCount() { return albumCount; }
    public void setAlbumCount(Integer albumCount) { this.albumCount = albumCount; }
}