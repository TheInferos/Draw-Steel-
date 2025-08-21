package com.drawsteel.controller;

import com.drawsteel.model.Complication;
import com.drawsteel.service.ComplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/complications")
public class ComplicationController {
    
    private final ComplicationService complicationService;
    
    @Autowired
    public ComplicationController(ComplicationService complicationService) {
        this.complicationService = complicationService;
    }
    
    @GetMapping
    public ResponseEntity<List<Complication>> getAllComplications() {
        List<Complication> complications = complicationService.getAllComplications();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Complication> getComplicationById(@PathVariable UUID id) {
        Optional<Complication> complication = complicationService.getComplicationById(id);
        return complication.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Complication> getComplicationByName(@PathVariable String name) {
        Optional<Complication> complication = complicationService.getComplicationByName(name);
        return complication.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/with-benefit")
    public ResponseEntity<List<Complication>> getComplicationsWithBenefit() {
        List<Complication> complications = complicationService.getComplicationsWithBenefit();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/with-drawback")
    public ResponseEntity<List<Complication>> getComplicationsWithDrawback() {
        List<Complication> complications = complicationService.getComplicationsWithDrawback();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/without-benefit-or-drawback")
    public ResponseEntity<List<Complication>> getComplicationsWithoutBenefitOrDrawback() {
        List<Complication> complications = complicationService.getComplicationsWithoutBenefitOrDrawback();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/with-combined-benefit-drawback")
    public ResponseEntity<List<Complication>> getComplicationsWithCombinedBenefitDrawback() {
        List<Complication> complications = complicationService.getComplicationsWithCombinedBenefitDrawback();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Complication>> searchComplications(@RequestParam String term) {
        List<Complication> complications = complicationService.searchComplications(term);
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search-benefit-drawback")
    public ResponseEntity<List<Complication>> searchByBenefitOrDrawback(@RequestParam String term) {
        List<Complication> complications = complicationService.searchByBenefitOrDrawback(term);
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search-combined-benefit-drawback")
    public ResponseEntity<List<Complication>> searchByCombinedBenefitDrawback(@RequestParam String term) {
        List<Complication> complications = complicationService.searchByCombinedBenefitDrawback(term);
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search-all-benefit-drawback")
    public ResponseEntity<List<Complication>> searchByAllBenefitDrawbackFields(@RequestParam String term) {
        List<Complication> complications = complicationService.searchByAllBenefitDrawbackFields(term);
        return ResponseEntity.ok(complications);
    }
    
    @PostMapping
    public ResponseEntity<Complication> createComplication(@RequestBody Complication complication) {
        try {
            Complication createdComplication = complicationService.createComplication(complication);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComplication);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Complication> updateComplication(@PathVariable UUID id, @RequestBody Complication complicationDetails) {
        try {
            Complication updatedComplication = complicationService.updateComplication(id, complicationDetails);
            return ResponseEntity.ok(updatedComplication);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplication(@PathVariable UUID id) {
        complicationService.deleteComplication(id);
        return ResponseEntity.noContent().build();
    }
}
