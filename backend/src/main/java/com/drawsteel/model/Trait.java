package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "traits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trait {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
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
}
