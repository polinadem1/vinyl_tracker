package com.vinyl.vinyl_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.repository.CollectorRepository;
import com.vinyl.vinyl_management.service.CollectorService;

@Service
@Transactional
public class CollectorServiceImpl implements CollectorService {
    
    @Autowired
    private CollectorRepository collectorRepository;
    
    @Override
    public List<Collector> findAll() {
        return collectorRepository.findAll();
    }
    
    @Override
    public Optional<Collector> findById(Long id) {
        return collectorRepository.findById(id);
    }
    
    @Override
    public Collector save(Collector collector) {
        return collectorRepository.save(collector);
    }
    
    @Override
    public void deleteById(Long id) {
        collectorRepository.deleteById(id);
    }
    
    @Override
    public Optional<Collector> findByUsername(String username) {
        return collectorRepository.findByUsername(username);
    }
    
    @Override
    public Optional<Collector> findByEmail(String email) {
        return collectorRepository.findByEmail(email);
    }
    
    @Override
    public List<Collector> findByDisplayNameContaining(String displayName) {
        return collectorRepository.findByDisplayNameContainingIgnoreCase(displayName);
    }
    
    // Дополнительные методы
    public boolean existsByEmail(String email) {
        return collectorRepository.existsByEmail(email);
    }
    
    public boolean existsByUsername(String username) {
        return collectorRepository.existsByUsername(username);
    }
    
    public List<Collector> findCollectorsWithCollection() {
        return collectorRepository.findCollectorsWithCollection();
    }
    
    public List<Collector> findCollectorsWithWishlist() {
        return collectorRepository.findCollectorsWithWishlist();
    }
    
    public List<Collector> findByDisplayNameOrUsername(String name) {
        return collectorRepository.findByDisplayNameOrUsernameContainingIgnoreCase(name);
    }
    
    public List<Collector> findCollectorsWithAvatar() {
        return collectorRepository.findByAvatarUrlIsNotNull();
    }
    
    public List<Collector> findCollectorsWithoutAvatar() {
        return collectorRepository.findByAvatarUrlIsNull();
    }
}