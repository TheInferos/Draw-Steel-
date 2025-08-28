package com.drawsteel.service;

import com.drawsteel.model.Language;
import com.drawsteel.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LanguageService extends BaseServiceImpl<Language, LanguageRepository> {
    
    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        super(languageRepository);
    }
    
    @Override
    public Language create(Language language) {
        if (getByName(language.getName()).isPresent()) {
            throw new IllegalArgumentException("Language with name '" + language.getName() + "' already exists");
        }
        return super.create(language);
    }
    
    @Override
    public Language update(UUID id, Language languageDetails) {
        if (languageDetails.getName() != null) {
            Optional<Language> existingWithName = getByName(languageDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Language with name '" + languageDetails.getName() + "' already exists");
            }
        }
        return super.update(id, languageDetails);
    }
}
