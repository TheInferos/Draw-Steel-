package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.drawsteel.model.enums.Weapon;
import com.drawsteel.model.enums.Armor;
import java.util.UUID;

@Entity
@Table(name = "kits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
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
    
    @Column(columnDefinition = "TEXT")
    private String signatureAbility;
}
