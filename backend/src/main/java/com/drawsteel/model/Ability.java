package com.drawsteel.model;

import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.model.enums.AbilityKeyword;
import com.drawsteel.model.enums.Area;
import com.drawsteel.model.enums.Condition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Table(name = "abilities")
@Data
@EqualsAndHashCode(callSuper = true)
public class Ability extends BaseModel {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AbilityType type;
    
    @ElementCollection(targetClass = AbilityKeyword.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "ability_keywords", joinColumns = @JoinColumn(name = "ability_id"))
    @Column(name = "keyword")
    private List<AbilityKeyword> keywords;
    
    @ElementCollection(targetClass = Condition.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "ability_conditions", joinColumns = @JoinColumn(name = "ability_id"))
    @Column(name = "condition")
    private List<Condition> conditions;
    
    @Column(nullable = false)
    private Boolean heroic = false;
    
    @Column(nullable = false)
    private Boolean signature = false;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Area area = Area.NONE;
    
    @Column(columnDefinition = "TEXT")
    private String target;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String ability;
    
    @Column(columnDefinition = "TEXT")
    private String trigger;
    
    @Column
    private Integer cooldown;
    
    @Column
    private String resourceCost;
    
    @Column
    private Integer range;
    
    @Column
    private Integer duration;
}
