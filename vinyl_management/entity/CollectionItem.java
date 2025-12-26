package com.vinyl.vinyl_management.entity;

import java.time.LocalDate;
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
@Table(name = "collection_items")
public class CollectionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "vinyl_release_id", nullable = false)
    private VinylRelease vinylRelease;
    
    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = false)
    private Collector collector;
    
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    
    @Column(name = "purchase_price")
    private Double purchasePrice;
    
    @Column(name = "current_value")
    private Double currentValue;
    
    // ИЗМЕНИТЕ НА String:
    @Column(name = "media_condition")
    private String mediaCondition;
    
    // ИЗМЕНИТЕ НА String:
    @Column(name = "sleeve_condition")
    private String sleeveCondition;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "added_at")
    private LocalDateTime addedAt;
    
    // Конструкторы
    public CollectionItem() {
        this.addedAt = LocalDateTime.now();
    }
    
    public CollectionItem(VinylRelease vinylRelease, Collector collector) {
        this.vinylRelease = vinylRelease;
        this.collector = collector;
        this.addedAt = LocalDateTime.now();
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public VinylRelease getVinylRelease() { return vinylRelease; }
    public void setVinylRelease(VinylRelease vinylRelease) { this.vinylRelease = vinylRelease; }
    
    public Collector getCollector() { return collector; }
    public void setCollector(Collector collector) { this.collector = collector; }
    
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    
    public Double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(Double purchasePrice) { this.purchasePrice = purchasePrice; }
    
    public Double getCurrentValue() { return currentValue; }
    public void setCurrentValue(Double currentValue) { this.currentValue = currentValue; }
    
    // ИЗМЕНИТЕ НА String:
    public String getMediaCondition() { return mediaCondition; }
    public void setMediaCondition(String mediaCondition) { this.mediaCondition = mediaCondition; }
    
    // ИЗМЕНИТЕ НА String:
    public String getSleeveCondition() { return sleeveCondition; }
    public void setSleeveCondition(String sleeveCondition) { this.sleeveCondition = sleeveCondition; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
