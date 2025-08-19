package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "traits")
@Data
@EqualsAndHashCode(callSuper = true)
public class Trait extends BaseModel {
    
    @Column(nullable = false)
    private Integer cost;
    
    @Column(nullable = false)
    private Boolean signatureToggle;
    
    @Column(nullable = false)
    private String traitType;
    
    @Column(columnDefinition = "TEXT")
    private String effect;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ancestry_id")
    private Ancestry ancestry;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trait_abilities",
        joinColumns = @JoinColumn(name = "trait_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<Ability> abilities = new HashSet<>();
    
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
    
    public boolean hasAbilities() {
        return abilities != null && !abilities.isEmpty();
    }
}
