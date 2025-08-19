package com.drawsteel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import com.drawsteel.model.character_classes.CharacterClass;
import com.drawsteel.model.enums.Skill;

@Entity
@Table(name = "characters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Character extends BaseModel {
    
    private static final int MIN_CHARACTERISTIC_SCORE = -5;
    
    private static final int MAX_CHARACTERISTIC_SCORE = 5;

    private static final int BASE_SPEED = 5;

    private static final int ZERO = 0;

    private static final int MIN_LEVEL = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ancestry_id")
    private Ancestry ancestry;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "culture_id")
    private Culture culture;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "career_id")
    private Career career;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kit_id")
    private Kit kit;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "character_class_id")
    private CharacterClass characterClass;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer level = MIN_LEVEL;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer might = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer agility = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer reason = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer intuition = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer presence = ZERO;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Integer speed = BASE_SPEED;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Integer stability = BASE_SPEED;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_abilities",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    @Builder.Default
    private Set<Ability> abilities = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_complications",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "complication_id")
    )
    @Builder.Default
    private Set<Complication> complications = new HashSet<>();

    public List<Skill> getAllSkills() {
        List<Skill> allSkills = new ArrayList<>();
        
        if (culture != null && culture.getSkill() != null) {
            allSkills.add(culture.getSkill());
        }
        
        if (career != null && career.getSkills() != null) {
            allSkills.addAll(career.getSkills());
        }
        
        return allSkills;
    }

    public void addAbility(Ability ability) {
        if (abilities == null) {
            abilities = new HashSet<>();
        }
        abilities.add(ability);
    }

    public void removeAbility(Ability ability) {
        if (abilities != null) {
            abilities.remove(ability);
        }
    }

    public boolean hasAbility(Ability ability) {
        return abilities != null && abilities.contains(ability);
    }

    public List<Ability> getHeroicAbilities() {
        if (abilities == null) {
            return new ArrayList<>();
        }
        return abilities.stream()
                .filter(Ability::getHeroic)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Ability> getSignatureAbilities() {
        if (abilities == null) {
            return new ArrayList<>();
        }
        return abilities.stream()
                .filter(Ability::getSignature)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Ability> getAbilitiesByType(com.drawsteel.model.enums.AbilityType type) {
        if (abilities == null) {
            return new ArrayList<>();
        }
        return abilities.stream()
                .filter(ability -> ability.getType() == type)
                .collect(java.util.stream.Collectors.toList());
    }

    public void addComplication(Complication complication) {
        if (complications == null) {
            complications = new HashSet<>();
        }
        complications.add(complication);
    }

    public void removeComplication(Complication complication) {
        if (complications == null) {
            complications.remove(complication);
        }
    }

    public boolean hasComplication(Complication complication) {
        return complications != null && complications.contains(complication);
    }

    public List<Complication> getComplicationsWithBenefit() {
        if (complications == null) {
            return new ArrayList<>();
        }
        return complications.stream()
                .filter(complication -> complication.getBenefit() != null && !complication.getBenefit().trim().isEmpty())
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Complication> getComplicationsWithDrawback() {
        if (complications == null) {
            return new ArrayList<>();
        }
        return complications.stream()
                .filter(complication -> complication.getDrawback() != null && !complication.getDrawback().trim().isEmpty())
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Complication> getComplicationsWithCombinedBenefitDrawback() {
        if (complications == null) {
            return new ArrayList<>();
        }
        return complications.stream()
                .filter(complication -> complication.getCombinedBenefitDrawback() != null && !complication.getCombinedBenefitDrawback().trim().isEmpty())
                .collect(java.util.stream.Collectors.toList());
    }
}
