package com.drawsteel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Character {
    
    private static final int MIN_CHARACTERISTIC_SCORE = -5;
    
    private static final int MAX_CHARACTERISTIC_SCORE = 5;

    private static final int BASE_SPEED = 5;

    private static final int ZERO = 0;

    private static final int MIN_LEVEL = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ancestry_id")
    private Ancestry ancestry;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer level = MIN_LEVEL;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer might = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer agility = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer reason = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer intuition = ZERO;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer presence = ZERO;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Integer speed = BASE_SPEED;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Integer stability = BASE_SPEED;

}
