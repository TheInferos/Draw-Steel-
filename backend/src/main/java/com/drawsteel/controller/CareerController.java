package com.drawsteel.controller;

import com.drawsteel.model.Career;
import com.drawsteel.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Career> createCareer(@RequestBody Career career) {
        Career savedCareer = careerService.saveCareer(career);
        return ResponseEntity.ok(savedCareer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Career> updateCareer(@PathVariable UUID id, @RequestBody Career career) {
        if (!careerService.getCareerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        career.setId(id);
        Career updatedCareer = careerService.saveCareer(career);
        return ResponseEntity.ok(updatedCareer);
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
