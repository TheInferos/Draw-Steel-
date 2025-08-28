package com.drawsteel.controller;

import com.drawsteel.model.Perk;
import com.drawsteel.model.enums.PerkType;
import com.drawsteel.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/perks")
public class PerkController extends BaseController<Perk> {
    
    private final PerkService perkService;
    
    @Autowired
    public PerkController(PerkService perkService) {
        super(perkService);
        this.perkService = perkService;
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
}
