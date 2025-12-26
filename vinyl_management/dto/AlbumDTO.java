package com.vinyl.vinyl_management.dto;

import java.util.List; // Добавьте этот импорт

public class AlbumDTO {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String catalogNumber;
    private String albumArtUrl;
    
    // Связи
    private Long artistId;
    private String artistName;
    private String artistCountry;
    
    private Long genreId;
    private String genreName;
    
    private List<VinylReleaseDTO> vinylReleases; 
    private Integer vinylReleaseCount;
    
    // Конструкторы
    public AlbumDTO() {}
    
    public AlbumDTO(Long id, String title, Integer releaseYear, String catalogNumber, 
                   String albumArtUrl, Long artistId, String artistName, 
                   Long genreId, String genreName) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.catalogNumber = catalogNumber;
        this.albumArtUrl = albumArtUrl;
        this.artistId = artistId;
        this.artistName = artistName;
        this.genreId = genreId;
        this.genreName = genreName;
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
    
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    
    public String getArtistCountry() { return artistCountry; }
    public void setArtistCountry(String artistCountry) { this.artistCountry = artistCountry; }
    
    public Long getGenreId() { return genreId; }
    public void setGenreId(Long genreId) { this.genreId = genreId; }
    
    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }
    
    public List<VinylReleaseDTO> getVinylReleases() { return vinylReleases; }
    public void setVinylReleases(List<VinylReleaseDTO> vinylReleases) { this.vinylReleases = vinylReleases; }
    
    public Integer getVinylReleaseCount() { return vinylReleaseCount; }
    public void setVinylReleaseCount(Integer vinylReleaseCount) { this.vinylReleaseCount = vinylReleaseCount; }
}