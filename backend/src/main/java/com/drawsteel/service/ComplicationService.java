package com.drawsteel.service;

import com.drawsteel.model.Complication;
import com.drawsteel.repository.ComplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComplicationService extends BaseServiceImpl<Complication, ComplicationRepository> {
    
    @Autowired
    public ComplicationService(ComplicationRepository complicationRepository) {
        super(complicationRepository);
    }
    
    public List<Complication> getComplicationsWithBenefit() {
        return repository.findByBenefitIsNotNull();
    }
    
    public List<Complication> getComplicationsWithDrawback() {
        return repository.findByDrawbackIsNotNull();
    }
    
    public List<Complication> getComplicationsWithoutBenefitOrDrawback() {
        return repository.findByBenefitIsNullAndDrawbackIsNull();
    }
    
    public List<Complication> getComplicationsWithCombinedBenefitDrawback() {
        return repository.findByCombinedBenefitDrawbackIsNotNull();
    }
    
    public List<Complication> searchComplications(String searchTerm) {
        return repository.searchByNameOrDescription(searchTerm);
    }
    
    public List<Complication> searchByBenefitOrDrawback(String searchTerm) {
        return repository.searchByBenefitOrDrawback(searchTerm);
    }
    
    public List<Complication> searchByCombinedBenefitDrawback(String searchTerm) {
        return repository.searchByCombinedBenefitDrawback(searchTerm);
    }
    
    public List<Complication> searchByAllBenefitDrawbackFields(String searchTerm) {
        return repository.searchByAllBenefitDrawbackFields(searchTerm);
    }
    
    @Override
    public Complication create(Complication complication) {
        if (getByName(complication.getName()).isPresent()) {
            throw new IllegalArgumentException("Complication with name '" + complication.getName() + "' already exists");
        }
        
        validateComplication(complication);
        
        return super.create(complication);
    }
    
    @Override
    public Complication update(UUID id, Complication complicationDetails) {
        // Check if the new name conflicts with another complication (excluding the current one)
        if (complicationDetails.getName() != null) {
            Optional<Complication> existingWithName = getByName(complicationDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Complication with name '" + complicationDetails.getName() + "' already exists");
            }
        }
        
        validateComplication(complicationDetails);
        
        return super.update(id, complicationDetails);
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
