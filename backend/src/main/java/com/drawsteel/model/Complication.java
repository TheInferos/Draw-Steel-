package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "complications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String benefit;
    
    @Column(columnDefinition = "TEXT")
    private String drawback;
    
    @Column(columnDefinition = "TEXT")
    private String combinedBenefitDrawback;
    
}
