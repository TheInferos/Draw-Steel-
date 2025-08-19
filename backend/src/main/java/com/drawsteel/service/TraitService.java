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
public class TraitService {
    
    private final TraitRepository traitRepository;
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public TraitService(TraitRepository traitRepository, AbilityRepository abilityRepository) {
        this.traitRepository = traitRepository;
        this.abilityRepository = abilityRepository;
    }
    
    public List<Trait> getAllTraits() {
        return traitRepository.findAll();
    }
    
    public Optional<Trait> getTraitById(UUID id) {
        return traitRepository.findById(id);
    }
    
    public Trait createTrait(Trait trait) {
        // Check if trait with the same name already exists
        if (traitRepository.findByName(trait.getName()).isPresent()) {
            throw new IllegalArgumentException("Trait with name '" + trait.getName() + "' already exists");
        }
        return traitRepository.save(trait);
    }
    
    public Trait updateTrait(UUID id, Trait traitDetails) {
        Optional<Trait> optionalTrait = traitRepository.findById(id);
        if (optionalTrait.isPresent()) {
            Trait existingTrait = optionalTrait.get();
            
            // Check if the new name conflicts with another trait (excluding the current one)
            Optional<Trait> existingWithName = traitRepository.findByName(traitDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Trait with name '" + traitDetails.getName() + "' already exists");
            }
            
            existingTrait.setName(traitDetails.getName());
            existingTrait.setDescription(traitDetails.getDescription());
            existingTrait.setCost(traitDetails.getCost());
            existingTrait.setSignatureToggle(traitDetails.getSignatureToggle());
            existingTrait.setTraitType(traitDetails.getTraitType());
            existingTrait.setEffect(traitDetails.getEffect());
            existingTrait.setAncestry(traitDetails.getAncestry());
            existingTrait.setAbilities(traitDetails.getAbilities());
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

    // Ability management methods
    public Trait addAbilityToTrait(UUID traitId, UUID abilityId) {
        Optional<Trait> optionalTrait = traitRepository.findById(traitId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalTrait.isPresent() && optionalAbility.isPresent()) {
            Trait trait = optionalTrait.get();
            Ability ability = optionalAbility.get();
            trait.addAbility(ability);
            return traitRepository.save(trait);
        }
        throw new RuntimeException("Trait or Ability not found");
    }

    public Trait removeAbilityFromTrait(UUID traitId, UUID abilityId) {
        Optional<Trait> optionalTrait = traitRepository.findById(traitId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalTrait.isPresent() && optionalAbility.isPresent()) {
            Trait trait = optionalTrait.get();
            Ability ability = optionalAbility.get();
            trait.removeAbility(ability);
            return traitRepository.save(trait);
        }
        throw new RuntimeException("Trait or Ability not found");
    }

    public List<Ability> getTraitAbilities(UUID traitId) {
        Optional<Trait> optionalTrait = traitRepository.findById(traitId);
        if (optionalTrait.isPresent()) {
            Trait trait = optionalTrait.get();
            return new ArrayList<>(trait.getAbilities());
        }
        throw new RuntimeException("Trait not found");
    }

    public List<Trait> getTraitsWithAbilities() {
        return traitRepository.findByAbilitiesIsNotNullAndAbilitiesIsNotEmpty();
    }

    public List<Trait> getTraitsWithoutAbilities() {
        return traitRepository.findByAbilitiesIsEmpty();
    }
}
