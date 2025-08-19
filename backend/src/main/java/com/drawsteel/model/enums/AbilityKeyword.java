package com.drawsteel.model.enums;

public enum AbilityKeyword {
    // Core Keywords
    AREA("Area", "Abilities with the Area keyword create an area of effect. Many area abilities deal damage to targets in their area, but such abilities are treated differently than strikes made against specific targets."),
    CHARGE("Charge", "Abilities with the Charge keyword can be used with the Charge main action instead of a melee free strike."),
    DRAW_STEEL("Draw Steel", "Core keyword for Draw Steel abilities"),
    MAGIC("Magic", "Abilities with the Magic keyword are used by characters who can cast spells, have innate magical features, or wield magic treasures. Such abilities do magical things such as create rays of fire, open swirling portals, or summon creatures."),
    MELEE("Melee", "Abilities with the Melee keyword can be used only over very short distances, typically within a character's reach, because they require a character to make contact with a creature or object with their body, a weapon, or an implement."),
    PSIONIC("Psionic", "Abilities with the Psionic keyword are used by characters who can manifest psionic powers, have innate psionic features, or wield psionic items. These abilities might create blasts of psychic energy, move objects with telekinesis, or slow down time with chronopathy."),
    RANGED("Ranged", "Abilities with the Ranged keyword can be used to affect creatures who are too far away to make contact with."),
    STRIKE("Strike", "Abilities with the Strike keyword (often referred to simply as 'strikes') deal damage to or impose a harmful effect on specific creatures or objects"),
    WEAPON("Weapon", "The Weapon keyword is used in abilities that must be used with a blade, a bow, or some other offensive weapon. Weapon abilities also include strikes creatures make with their own bodies, such as a character's unarmed strikes or a monster's punches, kicks, bites, tail slaps, and more.");
    
    private final String displayName;
    private final String description;
    
    AbilityKeyword(String displayName, String description) {
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
