package com.drawsteel.model.character_classes;

import com.drawsteel.model.Ability;
import com.drawsteel.model.BaseModel;
import com.drawsteel.model.Perk;
import com.drawsteel.model.enums.Skill;
import com.drawsteel.model.enums.SkillGroup;
import com.drawsteel.model.enums.AbilityScore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "character_classes")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CharacterClass extends BaseModel {
    
    @ElementCollection
    @CollectionTable(name = "class_starting_attributes", joinColumns = @JoinColumn(name = "class_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    private Map<AbilityScore, Integer> startingAttributes = new HashMap<>();

    @Column(name = "heroic_resource_name")
    protected String heroicResourceName;

    @Column(name = "heroic_resource_in_combat")
    protected String heroicResourceInCombat;
    
    @Column(name = "heroic_resource_out_of_combat")
    protected String heroicResourceOutOfCombat;

    protected List<String> subClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "potency")
    protected AbilityScore potency;


    @Column(name = "recoveries")
    protected Integer recoveries;

    @Column(name = "starting_stamina")
    protected Integer startingStamina;

    @Column(name = "stamina_per_level")
    protected Integer staminaPerLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "class_skills", joinColumns = @JoinColumn(name = "class_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    protected List<Skill> skills;

    @ElementCollection
    @Column(name = "ability")
    protected List<Ability> heroicAbilities;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "class_perks", joinColumns = @JoinColumn(name = "class_id"))
    protected List<Perk> perks;
    
    @ElementCollection
    @Column(name = "signature_abilities")
    protected List<Ability> signatureAbilitiesList;

    @Transient
    protected List<List<SkillGroup>> optionalSkills;

    public abstract void initializeStartingAttributes();
    
    public void setStartingAttribute(AbilityScore ability, Integer value) {
        if (startingAttributes == null) {
            startingAttributes = new HashMap<>();
        }
        startingAttributes.put(ability, value);
    }
    
    public Integer getStartingAttribute(AbilityScore ability) {
        return startingAttributes != null ? startingAttributes.get(ability) : 0;
    }
    
    public boolean hasStartingAttribute(AbilityScore ability) {
        return startingAttributes != null && startingAttributes.containsKey(ability);
    }
    
    public Map<AbilityScore, Integer> getStartingAttributes() {
        return startingAttributes != null ? new HashMap<>(startingAttributes) : new HashMap<>();
    }
    
    public abstract List<List<Integer>> getStartingAbilityScoreOptions(); 

}
