package com.drawsteel.service;

import com.drawsteel.model.Culture;
import com.drawsteel.repository.CultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CultureService {
    
    private final CultureRepository cultureRepository;
    
    @Autowired
    public CultureService(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }
    
    public List<Culture> getAllCultures() {
        return cultureRepository.findAll();
    }
    
    public Optional<Culture> getCultureById(UUID id) {
        return cultureRepository.findById(id);
    }
    
    public Culture createCulture(Culture culture) {
        // Check if culture with the same name already exists
        if (cultureRepository.findByName(culture.getName()).isPresent()) {
            throw new IllegalArgumentException("Culture with name '" + culture.getName() + "' already exists");
        }
        return cultureRepository.save(culture);
    }
    
    public Culture updateCulture(UUID id, Culture cultureDetails) {
        Optional<Culture> optionalCulture = cultureRepository.findById(id);
        if (optionalCulture.isPresent()) {
            Culture existingCulture = optionalCulture.get();
            
            // Check if the new name conflicts with another culture (excluding the current one)
            Optional<Culture> existingWithName = cultureRepository.findByName(cultureDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Culture with name '" + cultureDetails.getName() + "' already exists");
            }
            
            existingCulture.setName(cultureDetails.getName());
            existingCulture.setDescription(cultureDetails.getDescription());
            existingCulture.setLanguage(cultureDetails.getLanguage());
            existingCulture.setSkill(cultureDetails.getSkill());
            return cultureRepository.save(existingCulture);
        }
        throw new RuntimeException("Culture not found with id: " + id);
    }
    
    public void deleteCulture(UUID id) {
        cultureRepository.deleteById(id);
    }
}
