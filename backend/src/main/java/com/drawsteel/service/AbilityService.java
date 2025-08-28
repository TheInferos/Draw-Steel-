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
public class AbilityService extends BaseServiceImpl<Ability, AbilityRepository> {
    
    @Autowired
    public AbilityService(AbilityRepository abilityRepository) {
        super(abilityRepository);
    }
    
    public List<Ability> getAllAbilities() {
        return getAll();
    }
    
    public Optional<Ability> getAbilityById(UUID id) {
        return getById(id);
    }
    
    public Optional<Ability> getAbilityByName(String name) {
        return getByName(name);
    }
    
    public List<Ability> getAbilitiesByType(AbilityType type) {
        return repository.findByType(type);
    }
    
    public List<Ability> getHeroicAbilities() {
        return repository.findByHeroic(true);
    }
    
    public List<Ability> getSignatureAbilities() {
        return repository.findBySignature(true);
    }
    
    public List<Ability> getAbilitiesByArea(Area area) {
        return repository.findByArea(area);
    }
    
    public List<Ability> getAbilitiesByCondition(Condition condition) {
        return repository.findByCondition(condition);
    }
    
    public List<Ability> searchAbilities(String searchTerm) {
        return repository.searchByNameOrDescription(searchTerm);
    }
    
    public List<Ability> getHeroicSignatureAbilities() {
        return repository.findHeroicSignatureAbilities();
    }
    
    public List<Ability> getSpecialAbilities() {
        return repository.findSpecialAbilities();
    }
    
    public Ability createAbility(Ability ability) {
        // Validate ability data
        validateAbility(ability);
        return create(ability);
    }
    
    public Ability updateAbility(UUID id, Ability abilityDetails) {
        // Validate ability data
        validateAbility(abilityDetails);
        return update(id, abilityDetails);
    }
    
    public void deleteAbility(UUID id) {
        delete(id);
    }
    
    @Override
    public Optional<Ability> getByName(String name) {
        return repository.findByName(name);
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
