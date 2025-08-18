package com.drawsteel.service;

import com.drawsteel.model.Kit;
import com.drawsteel.model.enums.Armor;
import com.drawsteel.model.enums.Weapon;
import com.drawsteel.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KitService {
    
    private final KitRepository kitRepository;
    
    @Autowired
    public KitService(KitRepository kitRepository) {
        this.kitRepository = kitRepository;
    }
    
    public List<Kit> getAllKits() {
        return kitRepository.findAll();
    }
    
    public Optional<Kit> getKitById(UUID id) {
        return kitRepository.findById(id);
    }
    
    public Kit createKit(Kit kit) {
        // Check if kit with the same name already exists
        if (kitRepository.findByName(kit.getName()).isPresent()) {
            throw new IllegalArgumentException("Kit with name '" + kit.getName() + "' already exists");
        }
        
        // Validate armor and weapon enum values
        validateKitEnums(kit);
        
        return kitRepository.save(kit);
    }
    
    public Kit updateKit(UUID id, Kit kitDetails) {
        Optional<Kit> optionalKit = kitRepository.findById(id);
        if (optionalKit.isPresent()) {
            Kit existingKit = optionalKit.get();
            
            // Check if the new name conflicts with another kit (excluding the current one)
            Optional<Kit> existingWithName = kitRepository.findByName(kitDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Kit with name '" + kitDetails.getName() + "' already exists");
            }
            
            // Validate armor and weapon enum values
            validateKitEnums(kitDetails);
            
            existingKit.setName(kitDetails.getName());
            existingKit.setDescription(kitDetails.getDescription());
            existingKit.setArmor(kitDetails.getArmor());
            existingKit.setWeapon(kitDetails.getWeapon());
            existingKit.setStamina(kitDetails.getStamina());
            existingKit.setSpeed(kitDetails.getSpeed());
            existingKit.setStability(kitDetails.getStability());
            existingKit.setMeleeDamage(kitDetails.getMeleeDamage());
            existingKit.setRangedDamage(kitDetails.getRangedDamage());
            existingKit.setMeleeDistance(kitDetails.getMeleeDistance());
            existingKit.setRangedDistance(kitDetails.getRangedDistance());
            existingKit.setDisengage(kitDetails.getDisengage());
            existingKit.setSignatureAbility(kitDetails.getSignatureAbility());
            return kitRepository.save(existingKit);
        }
        throw new RuntimeException("Kit not found with id: " + id);
    }
    
    public void deleteKit(UUID id) {
        kitRepository.deleteById(id);
    }
    
    /**
     * Validates that the kit's armor and weapon values are valid enum values
     */
    private void validateKitEnums(Kit kit) {
        // Validate armor
        if (kit.getArmor() == null) {
            throw new IllegalArgumentException("Armor cannot be null");
        }
        
        try {
            Armor.valueOf(kit.getArmor().name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid armor value: " + kit.getArmor());
        }
        
        // Validate weapon
        if (kit.getWeapon() == null) {
            throw new IllegalArgumentException("Weapon cannot be null");
        }
        
        try {
            Weapon.valueOf(kit.getWeapon().name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid weapon value: " + kit.getWeapon());
        }
    }
}
