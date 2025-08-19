package com.drawsteel.service;

import com.drawsteel.model.Complication;
import com.drawsteel.repository.ComplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComplicationService {
    
    private final ComplicationRepository complicationRepository;
    
    @Autowired
    public ComplicationService(ComplicationRepository complicationRepository) {
        this.complicationRepository = complicationRepository;
    }
    
    public List<Complication> getAllComplications() {
        return complicationRepository.findAll();
    }
    
    public Optional<Complication> getComplicationById(UUID id) {
        return complicationRepository.findById(id);
    }
    
    public Optional<Complication> getComplicationByName(String name) {
        return complicationRepository.findByName(name);
    }
    
    public List<Complication> getComplicationsWithBenefit() {
        return complicationRepository.findByBenefitIsNotNull();
    }
    
    public List<Complication> getComplicationsWithDrawback() {
        return complicationRepository.findByDrawbackIsNotNull();
    }
    
    public List<Complication> getComplicationsWithoutBenefitOrDrawback() {
        return complicationRepository.findByBenefitIsNullAndDrawbackIsNull();
    }
    
    public List<Complication> getComplicationsWithCombinedBenefitDrawback() {
        return complicationRepository.findByCombinedBenefitDrawbackIsNotNull();
    }
    
    public List<Complication> searchComplications(String searchTerm) {
        return complicationRepository.searchByNameOrDescription(searchTerm);
    }
    
    public List<Complication> searchByBenefitOrDrawback(String searchTerm) {
        return complicationRepository.searchByBenefitOrDrawback(searchTerm);
    }
    
    public List<Complication> searchByCombinedBenefitDrawback(String searchTerm) {
        return complicationRepository.searchByCombinedBenefitDrawback(searchTerm);
    }
    
    public List<Complication> searchByAllBenefitDrawbackFields(String searchTerm) {
        return complicationRepository.searchByAllBenefitDrawbackFields(searchTerm);
    }
    
    public Complication createComplication(Complication complication) {
        if (complicationRepository.findByName(complication.getName()).isPresent()) {
            throw new IllegalArgumentException("Complication with name '" + complication.getName() + "' already exists");
        }
        
        validateComplication(complication);
        
        return complicationRepository.save(complication);
    }
    
    public Complication updateComplication(UUID id, Complication complicationDetails) {
        Optional<Complication> optionalComplication = complicationRepository.findById(id);
        if (optionalComplication.isPresent()) {
            Complication existingComplication = optionalComplication.get();
            
            Optional<Complication> existingWithName = complicationRepository.findByName(complicationDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Complication with name '" + complicationDetails.getName() + "' already exists");
            }
            
            validateComplication(complicationDetails);
            
            existingComplication.setName(complicationDetails.getName());
            existingComplication.setDescription(complicationDetails.getDescription());
            existingComplication.setBenefit(complicationDetails.getBenefit());
            existingComplication.setDrawback(complicationDetails.getDrawback());
            existingComplication.setCombinedBenefitDrawback(complicationDetails.getCombinedBenefitDrawback());
            
            return complicationRepository.save(existingComplication);
        }
        throw new RuntimeException("Complication not found with id: " + id);
    }
    
    public void deleteComplication(UUID id) {
        complicationRepository.deleteById(id);
    }
    
    private void validateComplication(Complication complication) {
        if (complication.getName() == null || complication.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Complication name cannot be null or empty");
        }
        
        if (complication.getDescription() == null || complication.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Complication description cannot be null or empty");
        }
        
        // Validate benefit/drawback fields
        validateBenefitDrawbackFields(complication);
    }
    
    private void validateBenefitDrawbackFields(Complication complication) {
        boolean hasSeparateFields = (complication.getBenefit() != null && !complication.getBenefit().trim().isEmpty()) ||
                                   (complication.getDrawback() != null && !complication.getDrawback().trim().isEmpty());
        boolean hasCombinedField = complication.getCombinedBenefitDrawback() != null && 
                                  !complication.getCombinedBenefitDrawback().trim().isEmpty();
        
        // Must have either separate fields OR combined field, but not both
        if (hasSeparateFields && hasCombinedField) {
            throw new IllegalArgumentException("Complication cannot have both separate benefit/drawback fields AND a combined field. Use either separate fields or the combined field, not both.");
        }
        
        // Must have at least one benefit/drawback field populated
        if (!hasSeparateFields && !hasCombinedField) {
            throw new IllegalArgumentException("Complication must have either separate benefit/drawback fields OR a combined benefit/drawback field. At least one must be populated.");
        }
        
        // If using separate fields, at least one must be populated
        if (hasSeparateFields && complication.getBenefit() == null && complication.getDrawback() == null) {
            throw new IllegalArgumentException("If using separate benefit/drawback fields, at least one must be populated.");
        }
    }
}
