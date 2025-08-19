package com.drawsteel.controller;

import com.drawsteel.model.Perk;
import com.drawsteel.model.enums.PerkType;
import com.drawsteel.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/perks")
@CrossOrigin(origins = "*")
public class PerkController {
    
    private final PerkService perkService;
    
    @Autowired
    public PerkController(PerkService perkService) {
        this.perkService = perkService;
    }
    
    @GetMapping
    public ResponseEntity<List<Perk>> getAllPerks() {
        List<Perk> perks = perkService.getAllPerks();
        return ResponseEntity.ok(perks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Perk> getPerkById(@PathVariable UUID id) {
        Optional<Perk> perk = perkService.getPerkById(id);
        return perk.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Perk> getPerkByName(@PathVariable String name) {
        Optional<Perk> perk = perkService.getPerkByName(name);
        return perk.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Perk>> getPerksByType(@PathVariable PerkType type) {
        List<Perk> perks = perkService.getPerksByType(type);
        return ResponseEntity.ok(perks);
    }
    
    @GetMapping("/with-ability")
    public ResponseEntity<List<Perk>> getPerksWithAbility() {
        List<Perk> perks = perkService.getPerksWithAbility();
        return ResponseEntity.ok(perks);
    }
    
    @GetMapping("/without-ability")
    public ResponseEntity<List<Perk>> getPerksWithoutAbility() {
        List<Perk> perks = perkService.getPerksWithoutAbility();
        return ResponseEntity.ok(perks);
    }
    
    @GetMapping("/type/{type}/with-ability")
    public ResponseEntity<List<Perk>> getPerksByTypeWithAbility(@PathVariable PerkType type) {
        List<Perk> perks = perkService.getPerksByTypeWithAbility(type);
        return ResponseEntity.ok(perks);
    }
    
    @GetMapping("/type/{type}/without-ability")
    public ResponseEntity<List<Perk>> getPerksByTypeWithoutAbility(@PathVariable PerkType type) {
        List<Perk> perks = perkService.getPerksByTypeWithoutAbility(type);
        return ResponseEntity.ok(perks);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Perk>> searchPerks(@RequestParam String term) {
        List<Perk> perks = perkService.searchPerks(term);
        return ResponseEntity.ok(perks);
    }
    
    @PostMapping
    public ResponseEntity<Perk> createPerk(@RequestBody Perk perk) {
        try {
            Perk createdPerk = perkService.createPerk(perk);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPerk);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Perk> updatePerk(@PathVariable UUID id, @RequestBody Perk perkDetails) {
        try {
            Perk updatedPerk = perkService.updatePerk(id, perkDetails);
            return ResponseEntity.ok(updatedPerk);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerk(@PathVariable UUID id) {
        perkService.deletePerk(id);
        return ResponseEntity.noContent().build();
    }
}
