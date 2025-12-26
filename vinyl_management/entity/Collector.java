package com.vinyl.vinyl_management.entity;

import java.time.LocalDateTime;
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
@Table(name = "collectors")
public class Collector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "display_name")
    private String displayName;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "avatar_url")
    private String avatarUrl;
    
    @Column(name = "role")
    private String role = "ROLE_USER";
    
    @OneToMany(mappedBy = "collector", cascade = CascadeType.ALL)
    private List<CollectionItem> collectionItems;
    
    @OneToMany(mappedBy = "collector", cascade = CascadeType.ALL)
    private List<WishlistItem> wishlistItems;
    
    // Конструкторы
    public Collector() {}
    
    public Collector(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.role = "ROLE_USER"; // по умолчанию
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public List<CollectionItem> getCollectionItems() { return collectionItems; }
    public void setCollectionItems(List<CollectionItem> collectionItems) { this.collectionItems = collectionItems; }
    
    public List<WishlistItem> getWishlistItems() { return wishlistItems; }
    public void setWishlistItems(List<WishlistItem> wishlistItems) { this.wishlistItems = wishlistItems; }
}