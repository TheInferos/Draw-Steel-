package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import com.drawsteel.model.enums.Skill;

@Entity
@Table(name = "careers")
@Data
@EqualsAndHashCode(callSuper = true)
public class Career extends BaseModel {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "career_skills", joinColumns = @JoinColumn(name = "career_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private List<Skill> skills = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "career_languages",
        joinColumns = @JoinColumn(name = "career_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages = new ArrayList<>();

    @Column(nullable = false)
    private int renown;

    @Column(nullable = false)
    private int wealth;

    @Column(nullable = false)
    private int projectPoints;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "career_perks",
        joinColumns = @JoinColumn(name = "career_id"),
        inverseJoinColumns = @JoinColumn(name = "perk_id")
    )
    private Set<Perk> perks = new HashSet<>();
    
    // Utility methods for managing perks
    public void addPerk(Perk perk) {
        if (perks == null) {
            perks = new HashSet<>();
        }
        perks.add(perk);
    }
    
    public void removePerk(Perk perk) {
        if (perks != null) {
            perks.remove(perk);
        }
    }
    
    public boolean hasPerk(Perk perk) {
        return perks != null && perks.contains(perk);
    }
    
    public boolean hasPerks() {
        return perks != null && !perks.isEmpty();
    }
    
    public List<Perk> getPerksByType(com.drawsteel.model.enums.PerkType type) {
        if (perks == null) {
            return new ArrayList<>();
        }
        return perks.stream()
                .filter(perk -> perk.getType() == type)
                .collect(java.util.stream.Collectors.toList());
    }
}
