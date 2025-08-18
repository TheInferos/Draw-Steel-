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
        return ancestryRepository.save(ancestry);
    }
    
    public Ancestry updateAncestry(UUID id, Ancestry ancestryDetails) {
        Optional<Ancestry> optionalAncestry = ancestryRepository.findById(id);
        if (optionalAncestry.isPresent()) {
            Ancestry existingAncestry = optionalAncestry.get();
            existingAncestry.setName(ancestryDetails.getName());
            existingAncestry.setDescription(ancestryDetails.getDescription());
            existingAncestry.setTraits(ancestryDetails.getTraits());
            return ancestryRepository.save(existingAncestry);
        }
        throw new RuntimeException("Ancestry not found with id: " + id);
    }
    
    public void deleteAncestry(UUID id) {
        ancestryRepository.deleteById(id);
    }
}
