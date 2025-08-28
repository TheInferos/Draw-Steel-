package com.drawsteel.service;

import com.drawsteel.model.Career;
import com.drawsteel.model.Perk;
import com.drawsteel.repository.CareerRepository;
import com.drawsteel.repository.PerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class CareerService extends BaseServiceImpl<Career, CareerRepository> {
    
    private final PerkRepository perkRepository;
    
    @Autowired
    public CareerService(CareerRepository careerRepository, PerkRepository perkRepository) {
        super(careerRepository);
        this.perkRepository = perkRepository;
    }
    
    @Override
    public Career create(Career career) {
        if (getByName(career.getName()).isPresent()) {
            throw new IllegalArgumentException("Career with name '" + career.getName() + "' already exists");
        }
        return super.create(career);
    }
    
    @Override
    public Career update(UUID id, Career careerDetails) {
        // Check if the new name conflicts with another career (excluding the current one)
        if (careerDetails.getName() != null) {
            Optional<Career> existingWithName = getByName(careerDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Career with name '" + careerDetails.getName() + "' already exists");
            }
        }
        return super.update(id, careerDetails);
    }
    
    // Perk management methods
    public Career addPerkToCareer(UUID careerId, UUID perkId) {
        Optional<Career> optionalCareer = getById(careerId);
        Optional<Perk> optionalPerk = perkRepository.findById(perkId);
        
        if (optionalCareer.isPresent() && optionalPerk.isPresent()) {
            Career career = optionalCareer.get();
            Perk perk = optionalPerk.get();
            career.addPerk(perk);
            return repository.save(career);
        }
        throw new RuntimeException("Career or Perk not found");
    }
    
    public Career removePerkFromCareer(UUID careerId, UUID perkId) {
        Optional<Career> optionalCareer = getById(careerId);
        Optional<Perk> optionalPerk = perkRepository.findById(perkId);
        
        if (optionalCareer.isPresent() && optionalPerk.isPresent()) {
            Career career = optionalCareer.get();
            Perk perk = optionalPerk.get();
            career.removePerk(perk);
            return repository.save(career);
        }
        throw new RuntimeException("Career or Perk not found");
    }
    
    public List<Perk> getCareerPerks(UUID careerId) {
        Optional<Career> optionalCareer = getById(careerId);
        if (optionalCareer.isPresent()) {
            Career career = optionalCareer.get();
            return new ArrayList<>(career.getPerks());
        }
        throw new RuntimeException("Career not found");
    }
    
    public List<Perk> getCareerPerksByType(UUID careerId, com.drawsteel.model.enums.PerkType type) {
        Optional<Career> optionalCareer = getById(careerId);
        if (optionalCareer.isPresent()) {
            Career career = optionalCareer.get();
            return career.getPerksByType(type);
        }
        throw new RuntimeException("Career not found");
    }
    
    public List<Career> getCareersWithPerks() {
        return repository.findByPerksIsNotNullAndPerksIsNotEmpty();
    }
    
    public List<Career> getCareersWithoutPerks() {
        return repository.findByPerksIsEmpty();
    }
}
