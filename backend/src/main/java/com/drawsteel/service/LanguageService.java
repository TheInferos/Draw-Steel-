package com.drawsteel.service;

import com.drawsteel.model.Language;
import com.drawsteel.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LanguageService {
    
    private final LanguageRepository languageRepository;
    
    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
    
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
    
    public Optional<Language> getLanguageById(UUID id) {
        return languageRepository.findById(id);
    }
    
    public Language createLanguage(Language language) {
        // Check if language with the same name already exists
        if (languageRepository.findByName(language.getName()).isPresent()) {
            throw new IllegalArgumentException("Language with name '" + language.getName() + "' already exists");
        }
        return languageRepository.save(language);
    }
    
    public Language updateLanguage(UUID id, Language languageDetails) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            Language existingLanguage = optionalLanguage.get();
            
            // Check if the new name conflicts with another language (excluding the current one)
            Optional<Language> existingWithName = languageRepository.findByName(languageDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Language with name '" + languageDetails.getName() + "' already exists");
            }
            
            existingLanguage.setName(languageDetails.getName());
            return languageRepository.save(existingLanguage);
        }
        throw new RuntimeException("Language not found with id: " + id);
    }
    
    public void deleteLanguage(UUID id) {
        languageRepository.deleteById(id);
    }
}
