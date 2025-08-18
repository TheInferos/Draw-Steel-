package com.drawsteel.controller;

import com.drawsteel.model.Trait;
import com.drawsteel.service.TraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/traits")
@CrossOrigin(origins = "*")
public class TraitController {
    
    private final TraitService traitService;
    
    @Autowired
    public TraitController(TraitService traitService) {
        this.traitService = traitService;
    }
    
    @GetMapping
    public ResponseEntity<List<Trait>> getAllTraits() {
        List<Trait> traits = traitService.getAllTraits();
        return ResponseEntity.ok(traits);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trait> getTraitById(@PathVariable UUID id) {
        return traitService.getTraitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createTrait(@RequestBody Trait trait) {
        try {
            Trait createdTrait = traitService.createTrait(trait);
            return ResponseEntity.ok(createdTrait);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrait(@PathVariable UUID id, @RequestBody Trait traitDetails) {
        try {
            Trait updatedTrait = traitService.updateTrait(id, traitDetails);
            return ResponseEntity.ok(updatedTrait);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrait(@PathVariable UUID id) {
        traitService.deleteTrait(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/ancestry/{ancestryId}")
    public ResponseEntity<List<Trait>> getTraitsByAncestry(@PathVariable UUID ancestryId) {
        List<Trait> traits = traitService.getTraitsByAncestryId(ancestryId);
        return ResponseEntity.ok(traits);
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
}
