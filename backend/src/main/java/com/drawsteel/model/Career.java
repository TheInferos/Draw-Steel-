package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.drawsteel.model.Perk;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "careers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Career {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "career_skills",
        joinColumns = @JoinColumn(name = "career_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
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
