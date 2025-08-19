package com.drawsteel.model;

import com.drawsteel.model.enums.PerkType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "perks")
@Data
@EqualsAndHashCode(callSuper = true)
public class Perk extends BaseModel {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerkType type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ability_id")
    private Ability ability;
}
