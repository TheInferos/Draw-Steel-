package com.drawsteel.controller;

import com.drawsteel.model.Trait;
import com.drawsteel.model.Ability;
import com.drawsteel.service.TraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/traits")
public class TraitController extends BaseController<Trait> {
    
    private final TraitService traitService;
    
    @Autowired
    public TraitController(TraitService traitService) {
        super(traitService);
        this.traitService = traitService;
    }
    
    @GetMapping("/cost/{cost}")
    public ResponseEntity<List<Trait>> getTraitsByCost(@PathVariable Integer cost) {
        List<Trait> traits = traitService.getTraitsByCost(cost);
        return ResponseEntity.ok(traits);
    }
    
    @GetMapping("/signature/{signatureToggle}")
    public ResponseEntity<List<Trait>> getTraitsBySignatureToggle(@PathVariable Boolean signatureToggle) {
        List<Trait> traits = traitService.getTraitsBySignatureToggle(signatureToggle);
        return ResponseEntity.ok(traits);
    }

    @PostMapping("/{traitId}/abilities/{abilityId}")
    public ResponseEntity<Trait> addAbilityToTrait(
            @PathVariable UUID traitId,
            @PathVariable UUID abilityId) {
        try {
            Trait updatedTrait = traitService.addAbilityToTrait(traitId, abilityId);
            return ResponseEntity.ok(updatedTrait);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{traitId}/abilities/{abilityId}")
    public ResponseEntity<Trait> removeAbilityFromTrait(
            @PathVariable UUID traitId,
            @PathVariable UUID abilityId) {
        try {
            Trait updatedTrait = traitService.removeAbilityFromTrait(traitId, abilityId);
            return ResponseEntity.ok(updatedTrait);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{traitId}/abilities")
    public ResponseEntity<List<Ability>> getTraitAbilities(@PathVariable UUID traitId) {
        try {
            List<Ability> abilities = traitService.getTraitAbilities(traitId);
            return ResponseEntity.ok(abilities);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/with-abilities")
    public ResponseEntity<List<Trait>> getTraitsWithAbilities() {
        List<Trait> traits = traitService.getTraitsWithAbilities();
        return ResponseEntity.ok(traits);
    }

    @GetMapping("/without-abilities")
    public ResponseEntity<List<Trait>> getTraitsWithoutAbilities() {
        List<Trait> traits = traitService.getTraitsWithoutAbilities();
        return ResponseEntity.ok(traits);
    }
}
