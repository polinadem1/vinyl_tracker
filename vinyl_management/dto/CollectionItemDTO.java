package com.vinyl.vinyl_management.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CollectionItemDTO {
    private Long id;
    
    // Связи
    private Long vinylReleaseId;
    private String vinylReleaseTitle;
    private String albumTitle;
    private String artistName;
    
    private Long collectorId;
    private String collectorUsername;
    
    private LocalDate purchaseDate;
    private Double purchasePrice;
    private Double currentValue;
    private String mediaCondition;
    private String sleeveCondition;
    private String notes;
    private LocalDateTime addedAt;
    
    // Конструкторы
    public CollectionItemDTO() {}
    
    public CollectionItemDTO(Long id, Long vinylReleaseId, Long collectorId) {
        this.id = id;
        this.vinylReleaseId = vinylReleaseId;
        this.collectorId = collectorId;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getVinylReleaseId() { return vinylReleaseId; }
    public void setVinylReleaseId(Long vinylReleaseId) { this.vinylReleaseId = vinylReleaseId; }
    
    public String getVinylReleaseTitle() { return vinylReleaseTitle; }
    public void setVinylReleaseTitle(String vinylReleaseTitle) { this.vinylReleaseTitle = vinylReleaseTitle; }
    
    public String getAlbumTitle() { return albumTitle; }
    public void setAlbumTitle(String albumTitle) { this.albumTitle = albumTitle; }
    
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    
    public Long getCollectorId() { return collectorId; }
    public void setCollectorId(Long collectorId) { this.collectorId = collectorId; }
    
    public String getCollectorUsername() { return collectorUsername; }
    public void setCollectorUsername(String collectorUsername) { this.collectorUsername = collectorUsername; }
    
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    
    public Double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(Double purchasePrice) { this.purchasePrice = purchasePrice; }
    
    public Double getCurrentValue() { return currentValue; }
    public void setCurrentValue(Double currentValue) { this.currentValue = currentValue; }
    
    public String getMediaCondition() { return mediaCondition; }
    public void setMediaCondition(String mediaCondition) { this.mediaCondition = mediaCondition; }
    
    public String getSleeveCondition() { return sleeveCondition; }
    public void setSleeveCondition(String sleeveCondition) { this.sleeveCondition = sleeveCondition; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}