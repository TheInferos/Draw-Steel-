package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.drawsteel.model.enums.Weapon;

import java.util.Map;

import com.drawsteel.model.enums.Armor;
import com.drawsteel.model.enums.Tier;

@Entity
@Table(name = "kits")
@Data
@EqualsAndHashCode(callSuper = true)
public class Kit extends BaseModel {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Armor armor;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Weapon weapon;
    
    @Column(nullable = false)
    private Integer stamina;
    
    @Column(nullable = false)
    private Integer speed;
    
    @Column(nullable = true)
    private Integer stability;
    
    @ElementCollection
    @CollectionTable(name = "kit_melee_damage", joinColumns = @JoinColumn(name = "kit_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "tier")
    @Column(name = "damage_value")
    private Map<Tier, Integer> meleeDamage;
    
    @ElementCollection
    @CollectionTable(name = "kit_ranged_damage", joinColumns = @JoinColumn(name = "kit_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "tier")
    @Column(name = "damage_value")
    private Map<Tier, Integer> rangedDamage;
    
    @Column(nullable = true)
    private Integer meleeDistance;
    
    @Column(nullable = true)
    private Integer rangedDistance;
    
    @Column(nullable = false)
    private Integer disengage;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "signature_ability_id")
    private Ability signatureAbility;
}
