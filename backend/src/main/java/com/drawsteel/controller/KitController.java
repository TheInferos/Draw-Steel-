package com.drawsteel.controller;

import com.drawsteel.model.Kit;
import com.drawsteel.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/kits")
@CrossOrigin(origins = "*")
public class KitController {
    
    private final KitService kitService;
    
    @Autowired
    public KitController(KitService kitService) {
        this.kitService = kitService;
    }
    
    @GetMapping
    public ResponseEntity<List<Kit>> getAllKits() {
        List<Kit> kits = kitService.getAllKits();
        return ResponseEntity.ok(kits);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Kit> getKitById(@PathVariable UUID id) {
        return kitService.getKitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createKit(@RequestBody Kit kit) {
        try {
            Kit createdKit = kitService.createKit(kit);
            return ResponseEntity.ok(createdKit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateKit(@PathVariable UUID id, @RequestBody Kit kitDetails) {
        try {
            Kit updatedKit = kitService.updateKit(id, kitDetails);
            return ResponseEntity.ok(updatedKit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKit(@PathVariable UUID id) {
        if (!kitService.getKitById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        kitService.deleteKit(id);
        return ResponseEntity.noContent().build();
    }
}
