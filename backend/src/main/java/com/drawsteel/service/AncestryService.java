package com.drawsteel.service;

import com.drawsteel.model.Ancestry;
import com.drawsteel.repository.AncestryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AncestryService {
    
    private final AncestryRepository ancestryRepository;
    
    @Autowired
    public AncestryService(AncestryRepository ancestryRepository) {
        this.ancestryRepository = ancestryRepository;
    }
    
    public List<Ancestry> getAllAncestries() {
        return ancestryRepository.findAll();
    }
    
    public Optional<Ancestry> getAncestryById(UUID id) {
        return ancestryRepository.findById(id);
    }
    
    public Ancestry createAncestry(Ancestry ancestry) {
        // Check if ancestry with the same name already exists
        if (ancestryRepository.findByName(ancestry.getName()).isPresent()) {
            throw new IllegalArgumentException("Ancestry with name '" + ancestry.getName() + "' already exists");
        }
        return ancestryRepository.save(ancestry);
    }
    
    public Ancestry updateAncestry(UUID id, Ancestry ancestryDetails) {
        Optional<Ancestry> optionalAncestry = ancestryRepository.findById(id);
        if (optionalAncestry.isPresent()) {
            Ancestry existingAncestry = optionalAncestry.get();
            
            // Check if the new name conflicts with another ancestry (excluding the current one)
            Optional<Ancestry> existingWithName = ancestryRepository.findByName(ancestryDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Ancestry with name '" + ancestryDetails.getName() + "' already exists");
            }
            
            existingAncestry.setName(ancestryDetails.getName());
            existingAncestry.setDescription(ancestryDetails.getDescription());
            existingAncestry.setBaseHealth(ancestryDetails.getBaseHealth());
            existingAncestry.setTraits(ancestryDetails.getTraits());
            return ancestryRepository.save(existingAncestry);
        }
        throw new RuntimeException("Ancestry not found with id: " + id);
    }
    
    public void deleteAncestry(UUID id) {
        ancestryRepository.deleteById(id);
    }
}
