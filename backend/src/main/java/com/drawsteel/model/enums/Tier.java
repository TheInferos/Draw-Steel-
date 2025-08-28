package com.drawsteel.model.enums;

public enum Tier {
    TIER1("Tier 1", "Fail result"),
    TIER2("Tier 2", "Success Result"),
    TIER3("Tier 3", "Additional Degree of Success");
    
    private final String displayName;
    private final String description;
    
    Tier(String displayName, String description) {
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
