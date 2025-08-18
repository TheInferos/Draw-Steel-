package com.drawsteel.service;

import com.drawsteel.model.Skill;
import com.drawsteel.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {
    
    private final SkillRepository skillRepository;
    
    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
    
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    
    public Optional<Skill> getSkillById(UUID id) {
        return skillRepository.findById(id);
    }
    
    public Skill createSkill(Skill skill) {
        // Check if skill with the same name already exists
        if (skillRepository.findByName(skill.getName()).isPresent()) {
            throw new IllegalArgumentException("Skill with name '" + skill.getName() + "' already exists");
        }
        return skillRepository.save(skill);
    }
    
    public Skill updateSkill(UUID id, Skill skillDetails) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isPresent()) {
            Skill existingSkill = optionalSkill.get();
            
            // Check if the new name conflicts with another skill (excluding the current one)
            Optional<Skill> existingWithName = skillRepository.findByName(skillDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Skill with name '" + skillDetails.getName() + "' already exists");
            }
            
            existingSkill.setName(skillDetails.getName());
            existingSkill.setDescription(skillDetails.getDescription());
            return skillRepository.save(existingSkill);
        }
        throw new RuntimeException("Skill not found with id: " + id);
    }
    
    public void deleteSkill(UUID id) {
        skillRepository.deleteById(id);
    }
}
