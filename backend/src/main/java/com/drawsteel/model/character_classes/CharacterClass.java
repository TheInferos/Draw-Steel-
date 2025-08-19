package com.drawsteel.model.character_classes;

import com.drawsteel.model.BaseModel;
import com.drawsteel.model.Skill;
import com.drawsteel.model.enums.AbilityScore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "character_classes")
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CharacterClass extends BaseModel {
    
    @ElementCollection
    @CollectionTable(name = "class_starting_attributes", joinColumns = @JoinColumn(name = "class_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    private Map<AbilityScore, Integer> startingAttributes = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "potency")
    private AbilityScore potency;


    @Column(name = "recoveries")
    protected Integer recoveries;

    @Column(name = "starting_stamina")
    protected Integer startingStamina;

    @Column(name = "stamina_per_level")
    protected Integer staminaPerLevel;

    protected List<Skill> skills;

    
    public abstract void initializeStartingAttributes();
    
    // Utility methods for starting attributes
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
