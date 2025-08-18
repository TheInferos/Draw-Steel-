package com.drawsteel.controller;

import com.drawsteel.model.Ancestry;
import com.drawsteel.service.AncestryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/ancestries")
@CrossOrigin(origins = "*")
public class AncestryController {
    
    private final AncestryService ancestryService;
    
    @Autowired
    public AncestryController(AncestryService ancestryService) {
        this.ancestryService = ancestryService;
    }
    
    @GetMapping
    public ResponseEntity<List<Ancestry>> getAllAncestries() {
        List<Ancestry> ancestries = ancestryService.getAllAncestries();
        return ResponseEntity.ok(ancestries);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ancestry> getAncestryById(@PathVariable UUID id) {
        return ancestryService.getAncestryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createAncestry(@RequestBody Ancestry ancestry) {
        try {
            Ancestry createdAncestry = ancestryService.createAncestry(ancestry);
            return ResponseEntity.ok(createdAncestry);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAncestry(@PathVariable UUID id, @RequestBody Ancestry ancestryDetails) {
        try {
            Ancestry updatedAncestry = ancestryService.updateAncestry(id, ancestryDetails);
            return ResponseEntity.ok(updatedAncestry);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAncestry(@PathVariable UUID id) {
        ancestryService.deleteAncestry(id);
        return ResponseEntity.noContent().build();
    }
}
