package com.drawsteel.service;

import com.drawsteel.model.Ancestry;
import com.drawsteel.repository.AncestryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class AncestryService extends BaseServiceImpl<Ancestry, AncestryRepository> {
    
    @Autowired
    public AncestryService(AncestryRepository ancestryRepository) {
        super(ancestryRepository);
    }
    
    @Override
    public Ancestry create(Ancestry ancestry) {
        // Check if ancestry with the same name already exists
        if (getByName(ancestry.getName()).isPresent()) {
            throw new IllegalArgumentException("Ancestry with name '" + ancestry.getName() + "' already exists");
        }
        return super.create(ancestry);
    }
    
    @Override
    public Ancestry update(UUID id, Ancestry ancestryDetails) {
        // Check if the new name conflicts with another ancestry (excluding the current one)
        if (ancestryDetails.getName() != null) {
            Optional<Ancestry> existingWithName = getByName(ancestryDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Ancestry with name '" + ancestryDetails.getName() + "' already exists");
            }
        }
        return super.update(id, ancestryDetails);
    }
}
