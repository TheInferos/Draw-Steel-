package com.drawsteel.model.character_classes;

import com.drawsteel.model.enums.AbilityScore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;


@Entity
@Table(name = "furies")
@Data
@EqualsAndHashCode(callSuper = true)
public class Fury extends CharacterClass {
    
    public Fury() {
        super();
        initializeStartingAttributes();
        this.startingStamina = 21;
        this.staminaPerLevel = 9;
        this.recoveries = 10;
    }
    
    @Override
    public void initializeStartingAttributes() {
        // Fury class starting attributes - focused on physical power and presence
        setStartingAttribute(AbilityScore.MIGHT, 2);
        setStartingAttribute(AbilityScore.AGILITY, 2); 
    }
    
    @Override
    public List<List<Integer>> getStartingAbilityScoreOptions() {
        return List.of(
            List.of(2, -1, -1),
            List.of(1, 1, -1),
            List.of(1, 0, 0)
        );
    }
    
}
