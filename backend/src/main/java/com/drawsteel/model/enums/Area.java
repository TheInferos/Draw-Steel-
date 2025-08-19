package com.drawsteel.model.enums;

public enum Area {
    NONE("None", "Single target ability"),
    AURA("Aura", "Creates an aura with a radius X that originates from you and moves with you for the duration. Targets must be within X squares of you."),
    BURST("Burst", "Creates a burst with radius X that originates from you and lasts only for as long as it takes to affect its targets. Targets must be within X squares of you."),
    CUBE("Cube", "Affects a cubic area with sides of length X. Targets must be within the cubic area to be affected."),
    LINE("Line", "Affects a linear area expressed as A × B line where A is length and B is width/height in squares. The area must be in a straight line."),
    WALL("Wall", "Creates a wall using X squares. Each square must share at least one side with another square of the wall. Can be stacked for height and blocks line of effect.");
    
    private final String displayName;
    private final String description;
    
    Area(String displayName, String description) {
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
