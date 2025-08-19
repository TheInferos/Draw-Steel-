package com.drawsteel.service;

import com.drawsteel.model.Character;
import com.drawsteel.model.Ability;
import com.drawsteel.repository.CharacterRepository;
import com.drawsteel.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class CharacterService {
    
    private final CharacterRepository characterRepository;
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public CharacterService(CharacterRepository characterRepository, AbilityRepository abilityRepository) {
        this.characterRepository = characterRepository;
        this.abilityRepository = abilityRepository;
    }
    
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }
    
    public Optional<Character> getCharacterById(UUID id) {
        return characterRepository.findById(id);
    }
    
    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }
    
    public Character updateCharacter(UUID id, Character characterDetails) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if (optionalCharacter.isPresent()) {
            Character existingCharacter = optionalCharacter.get();
            existingCharacter.setName(characterDetails.getName());
            existingCharacter.setDescription(characterDetails.getDescription());
            existingCharacter.setAncestry(characterDetails.getAncestry());
            existingCharacter.setCulture(characterDetails.getCulture());
            existingCharacter.setCareer(characterDetails.getCareer());
            existingCharacter.setKit(characterDetails.getKit());
            existingCharacter.setLevel(characterDetails.getLevel());
            existingCharacter.setMight(characterDetails.getMight());
            existingCharacter.setAgility(characterDetails.getAgility());
            existingCharacter.setReason(characterDetails.getReason());
            existingCharacter.setIntuition(characterDetails.getIntuition());
            existingCharacter.setPresence(characterDetails.getPresence());
            existingCharacter.setSpeed(characterDetails.getSpeed());
            existingCharacter.setStability(characterDetails.getStability());
            return characterRepository.save(existingCharacter);
        }
        throw new RuntimeException("Character not found with id: " + id);
    }
    
    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }

    public Character addAbilityToCharacter(UUID characterId, UUID abilityId) {
        Optional<Character> optionalCharacter = characterRepository.findById(characterId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalCharacter.isPresent() && optionalAbility.isPresent()) {
            Character character = optionalCharacter.get();
            Ability ability = optionalAbility.get();
            character.addAbility(ability);
            return characterRepository.save(character);
        }
        throw new RuntimeException("Character or Ability not found");
    }

    public Character removeAbilityFromCharacter(UUID characterId, UUID abilityId) {
        Optional<Character> optionalCharacter = characterRepository.findById(characterId);
        Optional<Ability> optionalAbility = abilityRepository.findById(abilityId);
        
        if (optionalCharacter.isPresent() && optionalAbility.isPresent()) {
            Character character = optionalCharacter.get();
            Ability ability = optionalAbility.get();
            character.removeAbility(ability);
            return characterRepository.save(character);
        }
        throw new RuntimeException("Character or Ability not found");
    }

    public List<Ability> getCharacterAbilities(UUID characterId) {
        Optional<Character> optionalCharacter = characterRepository.findById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return new ArrayList<>(character.getAbilities());
        }
        throw new RuntimeException("Character not found");
    }

    public List<Ability> getCharacterAbilitiesByType(UUID characterId, com.drawsteel.model.enums.AbilityType type) {
        Optional<Character> optionalCharacter = characterRepository.findById(characterId);
        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            return character.getAbilitiesByType(type);
        }
        throw new RuntimeException("Character not found");
    }
}
