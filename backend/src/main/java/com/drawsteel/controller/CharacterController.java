package com.drawsteel.controller;

import com.drawsteel.model.Character;
import com.drawsteel.model.Ability;
import com.drawsteel.model.Complication;
import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/characters")
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
        try {
            Character createdCharacter = characterService.createCharacter(character);
            return ResponseEntity.ok(createdCharacter);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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

    @PostMapping("/{characterId}/complications/{complicationId}")
    public ResponseEntity<Character> addComplicationToCharacter(
            @PathVariable UUID characterId,
            @PathVariable UUID complicationId) {
        try {
            Character updatedCharacter = characterService.addComplicationToCharacter(characterId, complicationId);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{characterId}/complications/{complicationId}")
    public ResponseEntity<Character> removeComplicationFromCharacter(
            @PathVariable UUID characterId,
            @PathVariable UUID complicationId) {
        try {
            Character updatedCharacter = characterService.removeComplicationFromCharacter(characterId, complicationId);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{characterId}/complications")
    public ResponseEntity<List<Complication>> getCharacterComplications(@PathVariable UUID characterId) {
        try {
            List<Complication> complications = characterService.getCharacterComplications(characterId);
            return ResponseEntity.ok(complications);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{characterId}/complications/with-benefit")
    public ResponseEntity<List<Complication>> getCharacterComplicationsWithBenefit(@PathVariable UUID characterId) {
        try {
            List<Complication> complications = characterService.getCharacterComplicationsWithBenefit(characterId);
            return ResponseEntity.ok(complications);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{characterId}/complications/with-drawback")
    public ResponseEntity<List<Complication>> getCharacterComplicationsWithDrawback(@PathVariable UUID characterId) {
        try {
            List<Complication> complications = characterService.getCharacterComplicationsWithDrawback(characterId);
            return ResponseEntity.ok(complications);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{characterId}/complications/with-combined-benefit-drawback")
    public ResponseEntity<List<Complication>> getCharacterComplicationsWithCombinedBenefitDrawback(@PathVariable UUID characterId) {
        try {
            List<Complication> complications = characterService.getCharacterComplicationsWithCombinedBenefitDrawback(characterId);
            return ResponseEntity.ok(complications);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
