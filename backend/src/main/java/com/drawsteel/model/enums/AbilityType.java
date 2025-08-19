package com.drawsteel.model.enums;

public enum AbilityType {
    ATTACK("Attack", "Abilities that deal damage or cause harm to targets"),
    DEFENSE("Defense", "Abilities that protect, heal, or provide defensive benefits"),
    MOVEMENT("Movement", "Abilities that enhance movement, positioning, or mobility"),
    UTILITY("Utility", "Abilities that provide non-combat benefits or special effects"),
    SOCIAL("Social", "Abilities that affect social interactions, persuasion, or influence"),
    MAGICAL("Magical", "Abilities that harness supernatural or mystical powers"),
    TECHNICAL("Technical", "Abilities that rely on skill, knowledge, or technology"),
    LEADERSHIP("Leadership", "Abilities that inspire, command, or coordinate allies");
    
    private final String displayName;
    private final String description;
    
    AbilityType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
