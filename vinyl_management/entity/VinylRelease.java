package com.vinyl.vinyl_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vinyl_releases")
public class VinylRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
    
    @Column(name = "release_country")
    private String releaseCountry;
    
    @Column(name = "label")
    private String label;
    
    @Column(name = "format")
    private String format;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "weight")
    private Integer weight;
    
    @Column(name = "limited_edition")
    private Boolean limitedEdition;
    
    @Column(name = "edition_size")
    private Integer editionSize;
    
    // Конструкторы
    public VinylRelease() {}
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }
    
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
}