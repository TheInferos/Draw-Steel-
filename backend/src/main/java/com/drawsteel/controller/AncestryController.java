package com.drawsteel.controller;

import com.drawsteel.model.Ancestry;
import com.drawsteel.service.AncestryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Ancestry> createAncestry(@RequestBody Ancestry ancestry) {
        Ancestry createdAncestry = ancestryService.createAncestry(ancestry);
        return ResponseEntity.ok(createdAncestry);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ancestry> updateAncestry(@PathVariable UUID id, @RequestBody Ancestry ancestryDetails) {
        try {
            Ancestry updatedAncestry = ancestryService.updateAncestry(id, ancestryDetails);
            return ResponseEntity.ok(updatedAncestry);
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
