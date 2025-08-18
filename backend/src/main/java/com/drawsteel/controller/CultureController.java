package com.drawsteel.controller;

import com.drawsteel.model.Culture;
import com.drawsteel.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cultures")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<Culture> createCulture(@RequestBody Culture culture) {
        Culture savedCulture = cultureService.saveCulture(culture);
        return ResponseEntity.ok(savedCulture);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Culture> updateCulture(@PathVariable UUID id, @RequestBody Culture culture) {
        if (!cultureService.getCultureById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        culture.setId(id);
        Culture updatedCulture = cultureService.saveCulture(culture);
        return ResponseEntity.ok(updatedCulture);
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
