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
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "ancestry_traits",
        joinColumns = @JoinColumn(name = "ancestry_id"),
        inverseJoinColumns = @JoinColumn(name = "trait_id")
    )
    private List<Trait> traits;
}
