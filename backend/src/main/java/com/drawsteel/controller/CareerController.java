package com.drawsteel.controller;

import com.drawsteel.model.Career;
import com.drawsteel.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins = "*")
public class CareerController {
    
    private final CareerService careerService;
    
    @Autowired
    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }
    
    @GetMapping
    public ResponseEntity<List<Career>> getAllCareers() {
        List<Career> careers = careerService.getAllCareers();
        return ResponseEntity.ok(careers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(@PathVariable UUID id) {
        return careerService.getCareerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createCareer(@RequestBody Career career) {
        try {
            Career createdCareer = careerService.createCareer(career);
            return ResponseEntity.ok(createdCareer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCareer(@PathVariable UUID id, @RequestBody Career careerDetails) {
        try {
            Career updatedCareer = careerService.updateCareer(id, careerDetails);
            return ResponseEntity.ok(updatedCareer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable UUID id) {
        if (!careerService.getCareerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        careerService.deleteCareer(id);
        return ResponseEntity.noContent().build();
    }
}
