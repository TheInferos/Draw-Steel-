package com.drawsteel.service;

import com.drawsteel.model.Culture;
import com.drawsteel.repository.CultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class CultureService extends BaseServiceImpl<Culture, CultureRepository> {
    
    @Autowired
    public CultureService(CultureRepository cultureRepository) {
        super(cultureRepository);
    }
    
    @Override
    public Culture create(Culture culture) {
        // Check if culture with the same name already exists
        if (getByName(culture.getName()).isPresent()) {
            throw new IllegalArgumentException("Culture with name '" + culture.getName() + "' already exists");
        }
        return super.create(culture);
    }
    
    @Override
    public Culture update(UUID id, Culture cultureDetails) {
        // Check if the new name conflicts with another culture (excluding the current one)
        if (cultureDetails.getName() != null) {
            Optional<Culture> existingWithName = getByName(cultureDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Culture with name '" + cultureDetails.getName() + "' already exists");
            }
        }
        return super.update(id, cultureDetails);
    }
}
