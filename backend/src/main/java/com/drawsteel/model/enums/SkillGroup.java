package com.drawsteel.model.enums;

public enum SkillGroup {
    CRAFTING("Crafting", "Skills related to creating and crafting items"),
    EXPLORATION("Exploration", "Skills for movement, survival, and physical activities"),
    INTERPERSONAL("Interpersonal", "Skills for social interaction and communication"),
    INTRIGUE("Intrigue", "Skills for stealth, deception, and covert operations"),
    LORE("Lore", "Knowledge skills about various subjects and domains");
    
    private final String displayName;
    private final String description;
    
    SkillGroup(String displayName, String description) {
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
