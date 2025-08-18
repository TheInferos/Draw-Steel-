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
    
    public Career saveCareer(Career career) {
        return careerRepository.save(career);
    }
    
    public void deleteCareer(UUID id) {
        careerRepository.deleteById(id);
    }
}
