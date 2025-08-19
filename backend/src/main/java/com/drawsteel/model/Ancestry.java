package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Table(name = "ancestries")
@Data
@EqualsAndHashCode(callSuper = true)
public class Ancestry extends BaseModel {
    
    @Column(nullable = false)
    private Integer baseHealth;
    
    @OneToMany(mappedBy = "ancestry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trait> traits;
}
