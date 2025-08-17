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
public abstract class Ancestry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "ancestry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trait> traits;
    
    // Abstract methods that subclasses must implement
    public abstract String getAncestryType();
    public abstract int getBaseHealth();
    public abstract int getBaseMana();
}
