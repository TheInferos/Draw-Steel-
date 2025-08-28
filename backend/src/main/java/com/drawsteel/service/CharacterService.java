package com.drawsteel.service;

import com.drawsteel.model.Character;
import com.drawsteel.model.Ability;
import com.drawsteel.model.Complication;
import com.drawsteel.repository.CharacterRepository;
import com.drawsteel.repository.AbilityRepository;
import com.drawsteel.repository.ComplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class CharacterService extends BaseServiceImpl<Character, CharacterRepository> {
    
    private final AbilityRepository abilityRepository;
    private final ComplicationRepository complicationRepository;
    
    @Autowired
    public CharacterService(CharacterRepository characterRepository, AbilityRepository abilityRepository, ComplicationRepository complicationRepository) {
        super(characterRepository);
        this.abilityRepository = abilityRepository;
        this.complicationRepository = complicationRepository;
    }
    
    @Override
    public Character create(Character character) {
        // Validate required fields
        if (character.getName() == null || character.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Character name cannot be null or empty");
        }
        
        return super.create(character);
    }
    
    @Override
    public Character update(UUID id, Character characterDetails) {
        // Validate required fields
        if (characterDetails.getName() != null && characterDetails.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Character name cannot be empty");
        }
        
        return super.update(id, characterDetails);
    }

    public Character addAbilityToCharacter(UUID characterId, UUID abilityId) {
        Optional<Character> optionalCharacter = getById(characterId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalCharacter.isPresent() && optionalAbility.isPresent()) {
            Character character = optionalCharacter.get();
            Ability ability = optionalAbility.get();
            character.addAbility(ability);
            return repository.save(character);
        }
        throw new RuntimeException("Character or Ability not found");
    }

    public Character removeAbilityFromCharacter(UUID characterId, UUID abilityId) {
        Optional<Character> optionalCharacter = getById(characterId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalCharacter.isPresent() && optionalAbility.isPresent()) {
            Character character = optionalCharacter.get();
            Ability ability = optionalAbility.get();
            character.removeAbility(ability);
            return repository.save(character);
        }
        throw new RuntimeException("Character or Ability not found");
    }

    public List<Ability> getCharacterAbilities(UUID characterId) {
        Optional<Character> optionalCharacter = getById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return new ArrayList<>(character.getAbilities());
        }
        throw new RuntimeException("Character not found");
    }

    public List<Ability> getCharacterAbilitiesByType(UUID characterId, com.drawsteel.model.enums.AbilityType type) {
        Optional<Character> optionalCharacter = getById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return character.getAbilitiesByType(type);
        }
        throw new RuntimeException("Character not found");
    }

    public Character addComplicationToCharacter(UUID characterId, UUID complicationId) {
        Optional<Character> optionalCharacter = getById(characterId);
        Optional<Complication> optionalComplication = complicationRepository.findById(complicationId);
        
        if (optionalCharacter.isPresent() && optionalComplication.isPresent()) {
            Character character = optionalCharacter.get();
            Complication complication = optionalComplication.get();
            character.addComplication(complication);
            return repository.save(character);
        }
        throw new RuntimeException("Character or Complication not found");
    }

    public Character removeComplicationFromCharacter(UUID characterId, UUID complicationId) {
        Optional<Character> optionalCharacter = getById(characterId);
        Optional<Complication> optionalComplication = complicationRepository.findById(complicationId);
        
        if (optionalCharacter.isPresent() && optionalComplication.isPresent()) {
            Character character = optionalCharacter.get();
            Complication complication = optionalComplication.get();
            character.removeComplication(complication);
            return repository.save(character);
        }
        throw new RuntimeException("Character or Complication not found");
    }

    public List<Complication> getCharacterComplications(UUID characterId) {
        Optional<Character> optionalCharacter = getById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return new ArrayList<>(character.getComplications());
        }
        throw new RuntimeException("Character not found");
    }

    public List<Complication> getCharacterComplicationsWithBenefit(UUID characterId) {
        Optional<Character> optionalCharacter = getById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return character.getComplicationsWithBenefit();
        }
        throw new RuntimeException("Character not found");
    }

    public List<Complication> getCharacterComplicationsWithDrawback(UUID characterId) {
        Optional<Character> optionalCharacter = getById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return character.getComplicationsWithDrawback();
        }
        throw new RuntimeException("Character not found");
    }

    public List<Complication> getCharacterComplicationsWithCombinedBenefitDrawback(UUID characterId) {
        Optional<Character> optionalCharacter = getById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return character.getComplicationsWithCombinedBenefitDrawback();
        }
        throw new RuntimeException("Character not found");
    }
}
