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

@Entity
@Table(name = "characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Character {
    
    private static final int MIN_CHARACTERISTIC_SCORE = -5;
    private static final int MAX_CHARACTERISTIC_SCORE = 5;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer level = 1;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer might = 0;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer agility = 0;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer reason = 0;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer intuition = 0;
    
    @NotNull
    @Min(MIN_CHARACTERISTIC_SCORE)
    @Max(MAX_CHARACTERISTIC_SCORE)
    @Column(nullable = false)
    @Builder.Default
    private Integer presence = 0;

}
