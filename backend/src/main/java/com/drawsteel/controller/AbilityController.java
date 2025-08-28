package com.drawsteel.controller;

import com.drawsteel.model.Ability;
import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.model.enums.Area;
import com.drawsteel.model.enums.Condition;
import com.drawsteel.service.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/abilities")
public class AbilityController extends BaseController<Ability> {
    
    private final AbilityService abilityService;
    
    @Autowired
    public AbilityController(AbilityService abilityService) {
        super(abilityService);
        this.abilityService = abilityService;
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Ability>> getAbilitiesByType(@PathVariable AbilityType type) {
        List<Ability> abilities = abilityService.getAbilitiesByType(type);
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/heroic")
    public ResponseEntity<List<Ability>> getHeroicAbilities() {
        List<Ability> abilities = abilityService.getHeroicAbilities();
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/signature")
    public ResponseEntity<List<Ability>> getSignatureAbilities() {
        List<Ability> abilities = abilityService.getSignatureAbilities();
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/area/{area}")
    public ResponseEntity<List<Ability>> getAbilitiesByArea(@PathVariable Area area) {
        List<Ability> abilities = abilityService.getAbilitiesByArea(area);
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/condition/{condition}")
    public ResponseEntity<List<Ability>> getAbilitiesByCondition(@PathVariable Condition condition) {
        List<Ability> abilities = abilityService.getAbilitiesByCondition(condition);
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Ability>> searchAbilities(@RequestParam String term) {
        List<Ability> abilities = abilityService.searchAbilities(term);
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/special")
    public ResponseEntity<List<Ability>> getSpecialAbilities() {
        List<Ability> abilities = abilityService.getSpecialAbilities();
        return ResponseEntity.ok(abilities);
    }
    
    @GetMapping("/heroic-signature")
    public ResponseEntity<List<Ability>> getHeroicSignatureAbilities() {
        List<Ability> abilities = abilityService.getHeroicSignatureAbilities();
        return ResponseEntity.ok(abilities);
    }
}
