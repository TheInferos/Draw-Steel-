package com.drawsteel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "complications")
@Data
@EqualsAndHashCode(callSuper = true)
public class Complication extends BaseModel {
    
    @Column(columnDefinition = "TEXT")
    private String benefit;
    
    @Column(columnDefinition = "TEXT")
    private String drawback;
    
    @Column(columnDefinition = "TEXT")
    private String combinedBenefitDrawback;
    
}
