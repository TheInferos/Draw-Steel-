package com.drawsteel.controller;

import com.drawsteel.model.Skill;
import com.drawsteel.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "*")
public class SkillController {
    
    private final SkillService skillService;
    
    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable UUID id) {
        return skillService.getSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skill) {
        try {
            Skill createdSkill = skillService.createSkill(skill);
            return ResponseEntity.ok(createdSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable UUID id, @RequestBody Skill skillDetails) {
        try {
            Skill updatedSkill = skillService.updateSkill(id, skillDetails);
            return ResponseEntity.ok(updatedSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID id) {
        if (!skillService.getSkillById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
