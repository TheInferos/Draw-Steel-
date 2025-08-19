package com.drawsteel.model.enums;

public enum Condition {
    // Combat Conditions
    BLEEDING("Bleeding", "While bleeding, whenever you use a main action, triggered action, or make a test/ability roll using Might or Agility, you lose Stamina equal to 1d6 + your level. This Stamina loss can't be prevented in any way."),
    DAZED("Dazed", "Can do only one thing on your turn: use a main action, use a maneuver, or use a move action. Cannot use triggered actions, free triggered actions, or free maneuvers."),
    FRIGHTENED("Frightened", "Any ability roll against the source of fear takes a bane. If that source is a creature, their ability rolls against you gain an edge. Cannot willingly move closer to the source of fear if you know their location."),
    GRABBED("Grabbed", "Speed 0, cannot be force moved except by what has you grabbed, cannot use Knockback maneuver, takes bane on abilities not targeting what has you grabbed. If grabbed by a creature and they move, you move with them."),
    PRONE("Prone", "Flat on the ground, strikes take a bane, melee abilities against you gain an edge. Must crawl to move (costs 1 additional square per square crawled). Cannot climb, jump, swim, or fly while prone."),
    RESTRAINED("Restrained", "Speed 0, cannot use Stand Up maneuver, cannot be force moved. Takes bane on ability rolls and Might/Agility tests, abilities against you gain an edge. Teleporting ends this condition."),
    SLOWED("Slowed", "Speed 2 unless already lower, cannot shift."),
    TAUNTED("Taunted", "Double bane on ability rolls for any ability that doesn't target the creature who taunted you, as long as you have line of effect to that creature."),
    WEAKENED("Weakened", "Takes a bane on power rolls.");
    
    private final String displayName;
    private final String description;
    
    Condition(String displayName, String description) {
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
