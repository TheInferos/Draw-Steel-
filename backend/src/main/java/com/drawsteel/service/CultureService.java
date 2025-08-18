package com.drawsteel.service;

import com.drawsteel.model.Culture;
import com.drawsteel.repository.CultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CultureService {
    
    private final CultureRepository cultureRepository;
    
    @Autowired
    public CultureService(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }
    
    public List<Culture> getAllCultures() {
        return cultureRepository.findAll();
    }
    
    public Optional<Culture> getCultureById(UUID id) {
        return cultureRepository.findById(id);
    }
    
    public Culture saveCulture(Culture culture) {
        return cultureRepository.save(culture);
    }
    
    public void deleteCulture(UUID id) {
        cultureRepository.deleteById(id);
    }
}
