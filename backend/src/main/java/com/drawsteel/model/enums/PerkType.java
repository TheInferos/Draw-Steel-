package com.drawsteel.model.enums;

public enum PerkType {
    CRAFTING("Crafting", "Improve your talent for crafting materials, and let you become an expert in the things you create"),
    EXPLORATION("Exploration", "Let you better traverse and explore different environments"),
    INTERPERSONAL("Interpersonal", "Improve your interactions with other creatures"),
    INTRIGUE("Intrigue", "Make you more effective at investigating mysteries and finding the truth, even as you keep your own secrets hidden"),
    LORE("Lore", "Improve your mastery of memory, language, and knowledge across a range of topics"),
    SUPERNATURAL("Supernatural", "Let you use magic and psionics to influence the world around you");
    
    private final String displayName;
    private final String description;
    
    PerkType(String displayName, String description) {
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
