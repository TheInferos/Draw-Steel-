package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ancestries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ancestry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private String ancestryType;
    
    @Column(nullable = false)
    private Integer baseHealth;
    
    @Column(nullable = false)
    private Integer baseMana;
    
    @OneToMany(mappedBy = "ancestry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trait> traits;
}
