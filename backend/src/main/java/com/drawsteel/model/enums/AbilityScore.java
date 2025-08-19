package com.drawsteel.model.enums;

public enum AbilityScore {
    MIGHT("Might", "Physical strength and power"),
    AGILITY("Agility", "Speed, reflexes, and coordination"),
    REASON("Reason", "Logic, analysis, and problem-solving"),
    INTUITION("Intuition", "Instinct, perception, and awareness"),
    PRESENCE("Presence", "Charisma, leadership, and force of personality");
    
    private final String displayName;
    private final String description;
    
    AbilityScore(String displayName, String description) {
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
