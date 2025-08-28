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
public class PerkService extends BaseServiceImpl<Perk, PerkRepository> {
    
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public PerkService(PerkRepository perkRepository, AbilityRepository abilityRepository) {
        super(perkRepository);
        this.abilityRepository = abilityRepository;
    }
    
    public List<Perk> getPerksByType(PerkType type) {
        return repository.findByType(type);
    }
    
    public List<Perk> getPerksWithAbility() {
        return repository.findByAbilityIsNotNull();
    }
    
    public List<Perk> getPerksWithoutAbility() {
        return repository.findByAbilityIsNull();
    }
    
    public List<Perk> searchPerks(String searchTerm) {
        return repository.searchByNameOrDescription(searchTerm);
    }
    
    public List<Perk> getPerksByTypeWithAbility(PerkType type) {
        return repository.findByTypeWithAbility(type);
    }
    
    public List<Perk> getPerksByTypeWithoutAbility(PerkType type) {
        return repository.findByTypeWithoutAbility(type);
    }
    
    @Override
    public Perk create(Perk perk) {
        if (getByName(perk.getName()).isPresent()) {
            throw new IllegalArgumentException("Perk with name '" + perk.getName() + "' already exists");
        }
        
        validatePerk(perk);
        
        return super.create(perk);
    }
    
    @Override
    public Perk update(UUID id, Perk perkDetails) {
        // Check if the new name conflicts with another perk (excluding the current one)
        if (perkDetails.getName() != null) {
            Optional<Perk> existingWithName = getByName(perkDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Perk with name '" + perkDetails.getName() + "' already exists");
            }
        }
        
        validatePerk(perkDetails);
        
        return super.update(id, perkDetails);
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
