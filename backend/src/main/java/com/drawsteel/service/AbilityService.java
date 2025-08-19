package com.drawsteel.service;

import com.drawsteel.model.Ability;
import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.model.enums.Area;
import com.drawsteel.model.enums.Condition;
import com.drawsteel.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AbilityService {
    
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public AbilityService(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }
    
    public List<Ability> getAllAbilities() {
        return abilityRepository.findAll();
    }
    
    public Optional<Ability> getAbilityById(UUID id) {
        return abilityRepository.findById(id);
    }
    
    public Optional<Ability> getAbilityByName(String name) {
        return abilityRepository.findByName(name);
    }
    
    public List<Ability> getAbilitiesByType(AbilityType type) {
        return abilityRepository.findByType(type);
    }
    
    public List<Ability> getHeroicAbilities() {
        return abilityRepository.findByHeroic(true);
    }
    
    public List<Ability> getSignatureAbilities() {
        return abilityRepository.findBySignature(true);
    }
    
    public List<Ability> getAbilitiesByArea(Area area) {
        return abilityRepository.findByArea(area);
    }
    
    public List<Ability> getAbilitiesByCondition(Condition condition) {
        return abilityRepository.findByCondition(condition);
    }
    
    public List<Ability> searchAbilities(String searchTerm) {
        return abilityRepository.searchByNameOrDescription(searchTerm);
    }
    
    public List<Ability> getHeroicSignatureAbilities() {
        return abilityRepository.findHeroicSignatureAbilities();
    }
    
    public List<Ability> getSpecialAbilities() {
        return abilityRepository.findSpecialAbilities();
    }
    
    public Ability createAbility(Ability ability) {
        // Check if ability with the same name already exists
        if (abilityRepository.findByName(ability.getName()).isPresent()) {
            throw new IllegalArgumentException("Ability with name '" + ability.getName() + "' already exists");
        }
        
        // Validate ability data
        validateAbility(ability);
        
        return abilityRepository.save(ability);
    }
    
    public Ability updateAbility(UUID id, Ability abilityDetails) {
        Optional<Ability> optionalAbility = abilityRepository.findById(id);
        if (optionalAbility.isPresent()) {
            Ability existingAbility = optionalAbility.get();
            
            // Check if the new name conflicts with another ability (excluding the current one)
            Optional<Ability> existingWithName = abilityRepository.findByName(abilityDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Ability with name '" + abilityDetails.getName() + "' already exists");
            }
            
            // Validate ability data
            validateAbility(abilityDetails);
            
            // Update fields
            existingAbility.setName(abilityDetails.getName());
            existingAbility.setDescription(abilityDetails.getDescription());
            existingAbility.setType(abilityDetails.getType());
            existingAbility.setKeywords(abilityDetails.getKeywords());
            existingAbility.setConditions(abilityDetails.getConditions());
            existingAbility.setHeroic(abilityDetails.getHeroic());
            existingAbility.setSignature(abilityDetails.getSignature());
            existingAbility.setArea(abilityDetails.getArea());
            existingAbility.setTarget(abilityDetails.getTarget());
            existingAbility.setAbility(abilityDetails.getAbility());
            existingAbility.setTrigger(abilityDetails.getTrigger());
            existingAbility.setCooldown(abilityDetails.getCooldown());
            existingAbility.setResourceCost(abilityDetails.getResourceCost());
            existingAbility.setRange(abilityDetails.getRange());
            existingAbility.setDuration(abilityDetails.getDuration());
            
            return abilityRepository.save(existingAbility);
        }
        throw new RuntimeException("Ability not found with id: " + id);
    }
    
    public void deleteAbility(UUID id) {
        abilityRepository.deleteById(id);
    }
    
    private void validateAbility(Ability ability) {
        if (ability.getName() == null || ability.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Ability name cannot be null or empty");
        }
        
        if (ability.getType() == null) {
            throw new IllegalArgumentException("Ability type cannot be null");
        }
        
        if (ability.getAbility() == null || ability.getAbility().trim().isEmpty()) {
            throw new IllegalArgumentException("Ability description cannot be null or empty");
        }
        
        if (ability.getHeroic() == null) {
            ability.setHeroic(false);
        }
        
        if (ability.getSignature() == null) {
            ability.setSignature(false);
        }
        
        if (ability.getArea() == null) {
            ability.setArea(Area.NONE);
        }
    }
}
