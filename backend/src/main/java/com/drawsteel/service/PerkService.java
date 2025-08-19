package com.drawsteel.service;

import com.drawsteel.model.Perk;
import com.drawsteel.model.enums.PerkType;
import com.drawsteel.repository.PerkRepository;
import com.drawsteel.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PerkService {
    
    private final PerkRepository perkRepository;
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public PerkService(PerkRepository perkRepository, AbilityRepository abilityRepository) {
        this.perkRepository = perkRepository;
        this.abilityRepository = abilityRepository;
    }
    
    public List<Perk> getAllPerks() {
        return perkRepository.findAll();
    }
    
    public Optional<Perk> getPerkById(UUID id) {
        return perkRepository.findById(id);
    }
    
    public Optional<Perk> getPerkByName(String name) {
        return perkRepository.findByName(name);
    }
    
    public List<Perk> getPerksByType(PerkType type) {
        return perkRepository.findByType(type);
    }
    
    public List<Perk> getPerksWithAbility() {
        return perkRepository.findByAbilityIsNotNull();
    }
    
    public List<Perk> getPerksWithoutAbility() {
        return perkRepository.findByAbilityIsNull();
    }
    
    public List<Perk> searchPerks(String searchTerm) {
        return perkRepository.searchByNameOrDescription(searchTerm);
    }
    
    public List<Perk> getPerksByTypeWithAbility(PerkType type) {
        return perkRepository.findByTypeWithAbility(type);
    }
    
    public List<Perk> getPerksByTypeWithoutAbility(PerkType type) {
        return perkRepository.findByTypeWithoutAbility(type);
    }
    
    public Perk createPerk(Perk perk) {
        if (perkRepository.findByName(perk.getName()).isPresent()) {
            throw new IllegalArgumentException("Perk with name '" + perk.getName() + "' already exists");
        }
        
        validatePerk(perk);
        
        return perkRepository.save(perk);
    }
    
    public Perk updatePerk(UUID id, Perk perkDetails) {
        Optional<Perk> optionalPerk = perkRepository.findById(id);
        if (optionalPerk.isPresent()) {
            Perk existingPerk = optionalPerk.get();
            
            Optional<Perk> existingWithName = perkRepository.findByName(perkDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Perk with name '" + perkDetails.getName() + "' already exists");
            }
            
            validatePerk(perkDetails);
            
            existingPerk.setName(perkDetails.getName());
            existingPerk.setDescription(perkDetails.getDescription());
            existingPerk.setType(perkDetails.getType());
            existingPerk.setAbility(perkDetails.getAbility());
            
            return perkRepository.save(existingPerk);
        }
        throw new RuntimeException("Perk not found with id: " + id);
    }
    
    public void deletePerk(UUID id) {
        perkRepository.deleteById(id);
    }
    
    private void validatePerk(Perk perk) {
        if (perk.getName() == null || perk.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Perk name cannot be null or empty");
        }
        
        if (perk.getDescription() == null || perk.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Perk description cannot be null or empty");
        }
        
        if (perk.getType() == null) {
            throw new IllegalArgumentException("Perk type cannot be null");
        }
        
        if (perk.getAbility() != null) {
            if (abilityRepository.findById(perk.getAbility().getId()).isEmpty()) {
                throw new IllegalArgumentException("Ability not found with id: " + perk.getAbility().getId());
            }
        }
    }
}
