package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

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

    @ElementCollection
    @CollectionTable(name = "career_perks", joinColumns = @JoinColumn(name = "career_id"))
    @Column(name = "perk")
    private List<String> perks = new ArrayList<>();
}
