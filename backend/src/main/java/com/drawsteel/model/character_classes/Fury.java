package com.drawsteel.model.character_classes;

import com.drawsteel.model.enums.AbilityScore;
import com.drawsteel.model.enums.Skill;
import com.drawsteel.model.enums.SkillGroup;
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
        this.heroicResourceName = "Rage";
        this.potency = AbilityScore.MIGHT;
        this.skills = List.of(Skill.NATURE);
        this.optionalSkills = List.of(List.of(SkillGroup.EXPLORATION, SkillGroup.INTRIGUE), List.of(SkillGroup.EXPLORATION, SkillGroup.INTRIGUE));
        this.heroicResourceInCombat = "At the start of a combat encounter or some other stressful situation tracked in combat rounds (as determined by the Director), you gain ferocity equal to your Victories. At the start of each of your turns during combat, you gain 1d3 ferocity. Additionally, the first time each combat round that you take damage, you gain 1 ferocity. The first time you become winded or are dying in an encounter, you gain 1d3 ferocity. You lose any remaining ferocity at the end of the encounter.";
        this.heroicResourceOutOfCombat = " Though you can’t gain ferocity outside of combat, you can use your heroic abilities and effects that cost ferocity without spending it. Whenever you use an ability or effect outside of combat that costs ferocity, you can’t use that same ability or effect outside of combat again until you earn 1 or more Victories or finish a respite. When you use an ability outside of combat that lets you spend unlimited ferocity on its effect, such as To the Uttermost End, you can use it as if you had spent an amount of ferocity equal to your Victories.";
        this.signatureAbilitiesList = List.of();
        initializeSubClasses();
    }
    
    private void initializeSubClasses() {
        this.subClass = List.of("Berserker","Reaver",  "Stormwight");
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
