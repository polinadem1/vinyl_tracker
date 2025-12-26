package com.vinyl.vinyl_management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist_items")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "vinyl_release_id", nullable = false)
    private VinylRelease vinylRelease;
    
    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = false)
    private Collector collector;
    
    @Column(name = "added_at")
    private LocalDateTime addedAt;
    
    @Column(name = "priority")
    private String priority;
    
    @Column(name = "max_price")
    private Double maxPrice;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    // Конструктор
    public WishlistItem() {
        this.addedAt = LocalDateTime.now();
        this.priority = "MEDIUM"; // значение по умолчанию
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public VinylRelease getVinylRelease() { return vinylRelease; }
    public void setVinylRelease(VinylRelease vinylRelease) { this.vinylRelease = vinylRelease; }
    
    public Collector getCollector() { return collector; }
    public void setCollector(Collector collector) { this.collector = collector; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
    
    // ИЗМЕНИТЕ НА String:
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
