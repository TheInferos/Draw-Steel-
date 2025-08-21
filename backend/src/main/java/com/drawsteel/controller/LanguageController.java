package com.drawsteel.controller;

import com.drawsteel.model.Language;
import com.drawsteel.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    
    private final LanguageService languageService;
    
    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }
    
    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        List<Language> languages = languageService.getAllLanguages();
        return ResponseEntity.ok(languages);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable UUID id) {
        return languageService.getLanguageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createLanguage(@RequestBody Language language) {
        try {
            Language createdLanguage = languageService.createLanguage(language);
            return ResponseEntity.ok(createdLanguage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLanguage(@PathVariable UUID id, @RequestBody Language languageDetails) {
        try {
            Language updatedLanguage = languageService.updateLanguage(id, languageDetails);
            return ResponseEntity.ok(updatedLanguage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable UUID id) {
        if (!languageService.getLanguageById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}
