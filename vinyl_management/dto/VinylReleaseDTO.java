package com.vinyl.vinyl_management.dto;

public class VinylReleaseDTO {
    private Long id;
    private String releaseCountry;
    private String label;
    private String format;
    private String color;
    private Integer weight;
    private Boolean limitedEdition;
    private Integer editionSize;
    
    // Связи
    private Long albumId;
    private String albumTitle;
    private String albumCatalogNumber;
    private String artistName;
    
    // Конструкторы
    public VinylReleaseDTO() {}
    
    public VinylReleaseDTO(Long id, String releaseCountry, String label, String format, 
                          String color, Integer weight, Boolean limitedEdition, 
                          Integer editionSize) {
        this.id = id;
        this.releaseCountry = releaseCountry;
        this.label = label;
        this.format = format;
        this.color = color;
        this.weight = weight;
        this.limitedEdition = limitedEdition;
        this.editionSize = editionSize;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getReleaseCountry() { return releaseCountry; }
    public void setReleaseCountry(String releaseCountry) { this.releaseCountry = releaseCountry; }
    
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public Integer getWeight() { return weight; }
    public void setWeight(Integer weight) { this.weight = weight; }
    
    public Boolean getLimitedEdition() { return limitedEdition; }
    public void setLimitedEdition(Boolean limitedEdition) { this.limitedEdition = limitedEdition; }
    
    public Integer getEditionSize() { return editionSize; }
    public void setEditionSize(Integer editionSize) { this.editionSize = editionSize; }
    
    public Long getAlbumId() { return albumId; }
    public void setAlbumId(Long albumId) { this.albumId = albumId; }
    
    public String getAlbumTitle() { return albumTitle; }
    public void setAlbumTitle(String albumTitle) { this.albumTitle = albumTitle; }
    
    public String getAlbumCatalogNumber() { return albumCatalogNumber; }
    public void setAlbumCatalogNumber(String albumCatalogNumber) { this.albumCatalogNumber = albumCatalogNumber; }
    
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
}