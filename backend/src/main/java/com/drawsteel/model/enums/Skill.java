package com.drawsteel.model.enums;

public enum Skill {
    // CRAFTING SKILLS
    ALCHEMY("Alchemy", SkillGroup.CRAFTING, "Make bombs and potions"),
    ARCHITECTURE("Architecture", SkillGroup.CRAFTING, "Create buildings and vehicles"),
    BLACKSMITHING("Blacksmithing", SkillGroup.CRAFTING, "Forge metal armor and weapons"),
    CARPENTRY("Carpentry", SkillGroup.CRAFTING, "Create items out of wood"),
    COOKING("Cooking", SkillGroup.CRAFTING, "Create delicious dishes"),
    FLETCHING("Fletching", SkillGroup.CRAFTING, "Make ranged weapons and ammunition"),
    FORGERY("Forgery", SkillGroup.CRAFTING, "Create false badges, documents, and other items"),
    JEWELRY("Jewelry", SkillGroup.CRAFTING, "Create bracelets, crowns, rings, and other jewelry"),
    MECHANICS("Mechanics", SkillGroup.CRAFTING, "Build machines and clockwork items"),
    TAILORING("Tailoring", SkillGroup.CRAFTING, "Craft clothing of cloth or leather"),
    
    // EXPLORATION SKILLS
    CLIMB("Climb", SkillGroup.EXPLORATION, "Move up vertical surfaces"),
    DRIVE("Drive", SkillGroup.EXPLORATION, "Control vehicles"),
    ENDURANCE("Endurance", SkillGroup.EXPLORATION, "Remain engaged in strenuous activity over a long period of time"),
    GYMNASTICS("Gymnastics", SkillGroup.EXPLORATION, "Move across unsteady or narrow surfaces; tumble"),
    HEAL("Heal", SkillGroup.EXPLORATION, "Use mundane first aid"),
    JUMP("Jump", SkillGroup.EXPLORATION, "Leap vertical and horizontal distances"),
    LIFT("Lift", SkillGroup.EXPLORATION, "Pick up, carry, and throw heavy objects"),
    NAVIGATE("Navigate", SkillGroup.EXPLORATION, "Read a map and travel without becoming lost"),
    RIDE("Ride", SkillGroup.EXPLORATION, "Ride and control a nonsapient mount, such as a horse"),
    SWIM("Swim", SkillGroup.EXPLORATION, "Move through deep liquid"),
    
    // INTERPERSONAL SKILLS
    BRAG("Brag", SkillGroup.INTERPERSONAL, "Impress others with stories of your deeds"),
    EMPATHIZE("Empathize", SkillGroup.INTERPERSONAL, "Relate to someone on a personal level"),
    FLIRT("Flirt", SkillGroup.INTERPERSONAL, "Attract romantic attention from someone"),
    GAMBLE("Gamble", SkillGroup.INTERPERSONAL, "Make bets with others"),
    HANDLE_ANIMALS("Handle Animals", SkillGroup.INTERPERSONAL, "Interact with nonsapient animal wildlife"),
    INTERROGATE("Interrogate", SkillGroup.INTERPERSONAL, "Obtain information from a creature withholding it"),
    INTIMIDATE("Intimidate", SkillGroup.INTERPERSONAL, "Awe or scare a creature"),
    LEAD("Lead", SkillGroup.INTERPERSONAL, "Inspire people to action"),
    LIE("Lie", SkillGroup.INTERPERSONAL, "Convince someone that a falsehood is true"),
    MUSIC("Music", SkillGroup.INTERPERSONAL, "Perform music vocally or with an instrument"),
    PERFORM("Perform", SkillGroup.INTERPERSONAL, "Engage in dance, oratory, acting, or some other physical performance"),
    PERSUADE("Persuade", SkillGroup.INTERPERSONAL, "Convince someone to agree with you through use of your charms and grace"),
    READ_PERSON("Read Person", SkillGroup.INTERPERSONAL, "Read the emotions and body language of other creatures"),
    
    // INTRIGUE SKILLS
    ALERTNESS("Alertness", SkillGroup.INTRIGUE, "Intuitively sense the details of your surroundings"),
    CONCEAL_OBJECT("Conceal Object", SkillGroup.INTRIGUE, "Hide an object on your person or in your environment"),
    DISGUISE("Disguise", SkillGroup.INTRIGUE, "Change your appearance to look like a different person"),
    EAVESDROP("Eavesdrop", SkillGroup.INTRIGUE, "Actively listen to something that is hard to hear, such as a whispered conversation through a door"),
    ESCAPE_ARTIST("Escape Artist", SkillGroup.INTRIGUE, "Escape from bonds such as rope or manacles"),
    HIDE("Hide", SkillGroup.INTRIGUE, "Conceal yourself from others' observation"),
    PICK_LOCK("Pick Lock", SkillGroup.INTRIGUE, "Open a lock without using the key"),
    PICK_POCKET("Pick Pocket", SkillGroup.INTRIGUE, "Steal an item that another person wears or carries without them noticing"),
    SABOTAGE("Sabotage", SkillGroup.INTRIGUE, "Disable a mechanical device such as a trap"),
    SEARCH("Search", SkillGroup.INTRIGUE, "Actively search an environment for important details and items"),
    SNEAK("Sneak", SkillGroup.INTRIGUE, "Move silently"),
    TRACK("Track", SkillGroup.INTRIGUE, "Follow a trail that another creature has left behind"),
    
    // LORE SKILLS
    CRIMINAL_UNDERWORLD("Criminal Underworld", SkillGroup.LORE, "Knowing about criminal organizations, their crimes, their relationships, and their leaders"),
    CULTURE("Culture", SkillGroup.LORE, "Knowing about a culture's customs, folktales, and taboos"),
    HISTORY("History", SkillGroup.LORE, "Knowing about significant past events"),
    MAGIC("Magic", SkillGroup.LORE, "Knowing about magical places, spells, rituals, items, and phenomena"),
    MONSTERS("Monsters", SkillGroup.LORE, "Knowing monster ecology, strengths, and weaknesses"),
    NATURE("Nature", SkillGroup.LORE, "Knowing about natural flora, fauna, and weather"),
    PSIONICS("Psionics", SkillGroup.LORE, "Knowing about psionic places, spells, rituals, items, and phenomena"),
    RELIGION("Religion", SkillGroup.LORE, "Knowing about religious mythology, practices, and rituals"),
    RUMORS("Rumors", SkillGroup.LORE, "Knowing gossip, legends, and uncertain truths"),
    SOCIETY("Society", SkillGroup.LORE, "Knowing noble etiquette and the leadership and power dynamics of noble families"),
    STRATEGY("Strategy", SkillGroup.LORE, "Knowing about battle tactics and logistics"),
    TIMESCAPE("Timescape", SkillGroup.LORE, "Knowing about the many worlds of the timescape");
    
    private final String displayName;
    private final SkillGroup skillGroup;
    private final String description;
    
    Skill(String displayName, SkillGroup skillGroup, String description) {
        this.displayName = displayName;
        this.skillGroup = skillGroup;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public SkillGroup getSkillGroup() {
        return skillGroup;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}