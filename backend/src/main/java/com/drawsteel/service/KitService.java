package com.drawsteel.service;

import com.drawsteel.model.Kit;
import com.drawsteel.model.Ability;
import com.drawsteel.model.enums.Armor;
import com.drawsteel.model.enums.Weapon;
import com.drawsteel.repository.KitRepository;
import com.drawsteel.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KitService extends BaseServiceImpl<Kit, KitRepository> {
    
    private final AbilityRepository abilityRepository;
    
    @Autowired
    public KitService(KitRepository kitRepository, AbilityRepository abilityRepository) {
        super(kitRepository);
        this.abilityRepository = abilityRepository;
    }
    
    @Override
    public Kit create(Kit kit) {
        if (getByName(kit.getName()).isPresent()) {
            throw new IllegalArgumentException("Kit with name '" + kit.getName() + "' already exists");
        }
        
        validateKitEnums(kit);
        
        validateSignatureAbility(kit);
        
        return super.create(kit);
    }
    
    @Override
    public Kit update(UUID id, Kit kitDetails) {
        // Check if the new name conflicts with another kit (excluding the current one)
        if (kitDetails.getName() != null) {
            Optional<Kit> existingWithName = getByName(kitDetails.getName());
            if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
                throw new IllegalArgumentException("Kit with name '" + kitDetails.getName() + "' already exists");
            }
        }
        
        validateKitEnums(kitDetails);
        validateSignatureAbility(kitDetails);
        
        return super.update(id, kitDetails);
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

    
    private void validateSignatureAbility(Kit kit) {
        if (kit.getSignatureAbility() != null) {
            Optional<Ability> ability = abilityRepository.findById(kit.getSignatureAbility().getId());
            if (ability.isEmpty()) {
                throw new IllegalArgumentException("Signature ability not found with id: " + kit.getSignatureAbility().getId());
            }
            
            if (!ability.get().getSignature()) {
                throw new IllegalArgumentException("Ability with id " + kit.getSignatureAbility().getId() + " is not a signature ability");
            }
        }
    }
}
