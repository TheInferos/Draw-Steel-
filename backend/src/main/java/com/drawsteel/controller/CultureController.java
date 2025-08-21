package com.drawsteel.controller;

import com.drawsteel.model.Culture;
import com.drawsteel.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/cultures")
public class CultureController {
    
    private final CultureService cultureService;
    
    @Autowired
    public CultureController(CultureService cultureService) {
        this.cultureService = cultureService;
    }
    
    @GetMapping
    public ResponseEntity<List<Culture>> getAllCultures() {
        List<Culture> cultures = cultureService.getAllCultures();
        return ResponseEntity.ok(cultures);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Culture> getCultureById(@PathVariable UUID id) {
        return cultureService.getCultureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createCulture(@RequestBody Culture culture) {
        try {
            Culture createdCulture = cultureService.createCulture(culture);
            return ResponseEntity.ok(createdCulture);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCulture(@PathVariable UUID id, @RequestBody Culture cultureDetails) {
        try {
            Culture updatedCulture = cultureService.updateCulture(id, cultureDetails);
            return ResponseEntity.ok(updatedCulture);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCulture(@PathVariable UUID id) {
        if (!cultureService.getCultureById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cultureService.deleteCulture(id);
        return ResponseEntity.noContent().build();
    }
}
