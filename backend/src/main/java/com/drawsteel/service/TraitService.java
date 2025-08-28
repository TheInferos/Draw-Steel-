package com.drawsteel.service;

import com.drawsteel.model.Trait;
import com.drawsteel.model.Ability;
import com.drawsteel.repository.TraitRepository;
import com.drawsteel.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class TraitService extends BaseServiceImpl<Trait, TraitRepository> {
    
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public TraitService(TraitRepository traitRepository, AbilityRepository abilityRepository) {
        super(traitRepository);
        this.abilityRepository = abilityRepository;
    }
    
    // Only keep the custom methods that aren't in the base service
    // All basic CRUD (create, update, delete, getById, getAll) are inherited
    
    public List<Trait> getTraitsByCost(Integer cost) {
        return repository.findByCost(cost);
    }
    
    public List<Trait> getTraitsBySignatureToggle(Boolean signatureToggle) {
        return repository.findBySignatureToggle(signatureToggle);
    }

    // Ability management methods
    public Trait addAbilityToTrait(UUID traitId, UUID abilityId) {
        Optional<Trait> optionalTrait = getById(traitId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalTrait.isPresent() && optionalAbility.isPresent()) {
            Trait trait = optionalTrait.get();
            Ability ability = optionalAbility.get();
            trait.addAbility(ability);
            return repository.save(trait);
        }
        throw new RuntimeException("Trait or Ability not found");
    }

    public Trait removeAbilityFromTrait(UUID traitId, UUID abilityId) {
        Optional<Trait> optionalTrait = getById(traitId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalTrait.isPresent() && optionalAbility.isPresent()) {
            Trait trait = optionalTrait.get();
            Ability ability = optionalAbility.get();
            trait.removeAbility(ability);
            return repository.save(trait);
        }
        throw new RuntimeException("Trait or Ability not found");
    }

    public List<Ability> getTraitAbilities(UUID traitId) {
        Optional<Trait> optionalTrait = getById(traitId);
        if (optionalTrait.isPresent()) {
            Trait trait = optionalTrait.get();
            return new ArrayList<>(trait.getAbilities());
        }
        throw new RuntimeException("Trait not found");
    }

    public List<Trait> getTraitsWithAbilities() {
        return repository.findByAbilitiesIsNotNullAndAbilitiesIsNotEmpty();
    }

    public List<Trait> getTraitsWithoutAbilities() {
        return repository.findByAbilitiesIsEmpty();
    }
    
    @Override
    public Optional<Trait> getByName(String name) {
        return repository.findByName(name);
    }
}
