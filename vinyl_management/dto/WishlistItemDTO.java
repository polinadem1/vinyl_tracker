package com.vinyl.vinyl_management.dto;

import java.time.LocalDateTime;

public class WishlistItemDTO {
    private Long id;
    
    // Связи
    private Long vinylReleaseId;
    private String vinylReleaseTitle;
    private String albumTitle;
    private String artistName;
    
    private Long collectorId;
    private String collectorUsername;
    
    private LocalDateTime addedAt;
    private String priority;
    private Double maxPrice;
    private String notes;
    
    // Конструкторы
    public WishlistItemDTO() {}
    
    public WishlistItemDTO(Long id, Long vinylReleaseId, Long collectorId) {
        this.id = id;
        this.vinylReleaseId = vinylReleaseId;
        this.collectorId = collectorId;
        this.addedAt = LocalDateTime.now();
        this.priority = "MEDIUM";
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
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}