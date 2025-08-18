package com.drawsteel.service;

import com.drawsteel.model.Career;
import com.drawsteel.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CareerService {
    
    private final CareerRepository careerRepository;
    
    @Autowired
    public CareerService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }
    
    public List<Career> getAllCareers() {
        return careerRepository.findAll();
    }
    
    public Optional<Career> getCareerById(UUID id) {
        return careerRepository.findById(id);
    }
    
    public Career createCareer(Career career) {
        if (careerRepository.findByName(career.getName()).isPresent()) {
            throw new IllegalArgumentException("Career with name '" + career.getName() + "' already exists");
        }
        return careerRepository.save(career);
    }
    
    public Career updateCareer(UUID id, Career careerDetails) {
        Optional<Career> optionalCareer = careerRepository.findById(id);
        if (optionalCareer.isPresent()) {
            Career existingCareer = optionalCareer.get();
            
            // Check if the new name conflicts with another career (excluding the current one)
            Optional<Career> existingWithName = careerRepository.findByName(careerDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Career with name '" + careerDetails.getName() + "' already exists");
            }
            
            existingCareer.setName(careerDetails.getName());
            existingCareer.setDescription(careerDetails.getDescription());
            existingCareer.setSkills(careerDetails.getSkills());
            existingCareer.setLanguages(careerDetails.getLanguages());
            existingCareer.setRenown(careerDetails.getRenown());
            existingCareer.setWealth(careerDetails.getWealth());
            existingCareer.setProjectPoints(careerDetails.getProjectPoints());
            existingCareer.setPerks(careerDetails.getPerks());
            return careerRepository.save(existingCareer);
        }
        throw new RuntimeException("Career not found with id: " + id);
    }
    
    public void deleteCareer(UUID id) {
        careerRepository.deleteById(id);
    }
}
