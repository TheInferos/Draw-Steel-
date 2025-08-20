package com.drawsteel.model.character_classes;

import com.drawsteel.model.BaseModel;
import com.drawsteel.model.enums.Skill;
import com.drawsteel.model.enums.AbilityScore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "fury_sub_classes")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CharacterSubClass extends BaseModel {
    
    @Column(name = "name", nullable = false)
    protected String name;
    
    @Column(name = "description")
    protected String description;
    
    @Column(name = "subclasss_skills")
    protected List<Skill> subClassSkills;
    @ElementCollection
    @CollectionTable(name = "subclass_skills", joinColumns = @JoinColumn(name = "subclass_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    protected List<Skill> additionalSkills;
    
    @ElementCollection
    @CollectionTable(name = "subclass_abilities", joinColumns = @JoinColumn(name = "subclass_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    protected Map<AbilityScore, Integer> abilityModifiers;
    
}
