package com.drawsteel.service;

import com.drawsteel.model.Trait;
import com.drawsteel.repository.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TraitService {
    
    private final TraitRepository traitRepository;
    
    @Autowired
    public TraitService(TraitRepository traitRepository) {
        this.traitRepository = traitRepository;
    }
    
    public List<Trait> getAllTraits() {
        return traitRepository.findAll();
    }
    
    public Optional<Trait> getTraitById(UUID id) {
        return traitRepository.findById(id);
    }
    
    public Trait createTrait(Trait trait) {
        return traitRepository.save(trait);
    }
    
    public Trait updateTrait(UUID id, Trait traitDetails) {
        Optional<Trait> optionalTrait = traitRepository.findById(id);
        if (optionalTrait.isPresent()) {
            Trait existingTrait = optionalTrait.get();
            existingTrait.setName(traitDetails.getName());
            existingTrait.setDescription(traitDetails.getDescription());
            existingTrait.setCost(traitDetails.getCost());
            existingTrait.setSignatureToggle(traitDetails.getSignatureToggle());
            existingTrait.setAncestry(traitDetails.getAncestry());
            return traitRepository.save(existingTrait);
        }
        throw new RuntimeException("Trait not found with id: " + id);
    }
    
    public void deleteTrait(UUID id) {
        traitRepository.deleteById(id);
    }
    
    public List<Trait> getTraitsByAncestryId(UUID ancestryId) {
        return traitRepository.findByAncestryId(ancestryId);
    }
    
    public List<Trait> getTraitsByCost(Integer cost) {
        return traitRepository.findByCost(cost);
    }
    
    public List<Trait> getTraitsBySignatureToggle(Boolean signatureToggle) {
        return traitRepository.findBySignatureToggle(signatureToggle);
    }
}
