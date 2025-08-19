package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.drawsteel.model.enums.Weapon;
import com.drawsteel.model.enums.Armor;

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
    
    @Column(nullable = false)
    private Integer stability;
    
    @Column(nullable = false)
    private Integer meleeDamage;
    
    @Column(nullable = false)
    private Integer rangedDamage;
    
    @Column(nullable = false)
    private Integer meleeDistance;
    
    @Column(nullable = false)
    private Integer rangedDistance;
    
    @Column(nullable = false)
    private Integer disengage;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "signature_ability_id")
    private Ability signatureAbility;
}
