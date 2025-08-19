package com.drawsteel.controller;

import com.drawsteel.model.Character;
import com.drawsteel.model.Ability;
import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "*")
public class CharacterController {
    
    private final CharacterService characterService;
    
    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }
    
    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable UUID id) {
        return characterService.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        Character createdCharacter = characterService.createCharacter(character);
        return ResponseEntity.ok(createdCharacter);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable UUID id, @RequestBody Character characterDetails) {
        try {
            Character updatedCharacter = characterService.updateCharacter(id, characterDetails);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    // Ability management endpoints
    @PostMapping("/{characterId}/abilities/{abilityId}")
    public ResponseEntity<Character> addAbilityToCharacter(
            @PathVariable UUID characterId,
            @PathVariable UUID abilityId) {
        try {
            Character updatedCharacter = characterService.addAbilityToCharacter(characterId, abilityId);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{characterId}/abilities/{abilityId}")
    public ResponseEntity<Character> removeAbilityFromCharacter(
            @PathVariable UUID characterId,
            @PathVariable UUID abilityId) {
        try {
            Character updatedCharacter = characterService.removeAbilityFromCharacter(characterId, abilityId);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{characterId}/abilities")
    public ResponseEntity<List<Ability>> getCharacterAbilities(@PathVariable UUID characterId) {
        try {
            List<Ability> abilities = characterService.getCharacterAbilities(characterId);
            return ResponseEntity.ok(abilities);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{characterId}/abilities/type/{type}")
    public ResponseEntity<List<Ability>> getCharacterAbilitiesByType(
            @PathVariable UUID characterId,
            @PathVariable AbilityType type) {
        try {
            List<Ability> abilities = characterService.getCharacterAbilitiesByType(characterId, type);
            return ResponseEntity.ok(abilities);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
