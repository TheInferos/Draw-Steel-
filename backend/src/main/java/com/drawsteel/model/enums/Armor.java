package com.drawsteel.model.enums;

public enum Armor {
    LIGHT("Light Armor", "Light armor provides minimal protection but maximum mobility"),
    MEDIUM("Medium Armor", "Medium armor provides balanced protection and mobility"),
    HEAVY("Heavy Armor", "Heavy armor provides maximum protection but reduced mobility"),
    SHIELD("Shield", "A shield provides additional protection when used with armor");
    
    private final String displayName;
    private final String description;
    
    Armor(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isLight() {
        return this == LIGHT;
    }
    
    public boolean isMedium() {
        return this == MEDIUM;
    }
    
    public boolean isHeavy() {
        return this == HEAVY;
    }
    
    public boolean isShield() {
        return this == SHIELD;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
