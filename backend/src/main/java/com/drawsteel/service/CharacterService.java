package com.drawsteel.service;

import com.drawsteel.model.Character;
import com.drawsteel.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {
    
    private final CharacterRepository characterRepository;
    
    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
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
}
