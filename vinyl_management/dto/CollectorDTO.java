package com.vinyl.vinyl_management.dto;

import java.time.LocalDateTime;

public class CollectorDTO {
    private Long id;
    private String username;
    private String email;
    private String displayName;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private Integer collectionItemCount;
    private Integer wishlistItemCount;
    private Double collectionTotalValue;
    
    // Конструкторы
    public CollectorDTO() {}
    
    public CollectorDTO(Long id, String username, String email, String displayName, 
                       String avatarUrl) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Integer getCollectionItemCount() { return collectionItemCount; }
    public void setCollectionItemCount(Integer collectionItemCount) { this.collectionItemCount = collectionItemCount; }
    
    public Integer getWishlistItemCount() { return wishlistItemCount; }
    public void setWishlistItemCount(Integer wishlistItemCount) { this.wishlistItemCount = wishlistItemCount; }
    
    public Double getCollectionTotalValue() { return collectionTotalValue; }
    public void setCollectionTotalValue(Double collectionTotalValue) { this.collectionTotalValue = collectionTotalValue; }
}