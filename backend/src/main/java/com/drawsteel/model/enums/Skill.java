package com.drawsteel.model.enums;

public enum Skill {
    // CRAFTING SKILLS
    ALCHEMY("Alchemy", "Crafting", "Make bombs and potions"),
    ARCHITECTURE("Architecture", "Crafting", "Create buildings and vehicles"),
    BLACKSMITHING("Blacksmithing", "Crafting", "Forge metal armor and weapons"),
    CARPENTRY("Carpentry", "Crafting", "Create items out of wood"),
    COOKING("Cooking", "Crafting", "Create delicious dishes"),
    FLETCHING("Fletching", "Crafting", "Make ranged weapons and ammunition"),
    FORGERY("Forgery", "Crafting", "Create false badges, documents, and other items"),
    JEWELRY("Jewelry", "Crafting", "Create bracelets, crowns, rings, and other jewelry"),
    MECHANICS("Mechanics", "Crafting", "Build machines and clockwork items"),
    TAILORING("Tailoring", "Crafting", "Craft clothing of cloth or leather"),
    
    // EXPLORATION SKILLS
    CLIMB("Climb", "Exploration", "Move up vertical surfaces"),
    DRIVE("Drive", "Exploration", "Control vehicles"),
    ENDURANCE("Endurance", "Exploration", "Remain engaged in strenuous activity over a long period of time"),
    GYMNASTICS("Gymnastics", "Exploration", "Move across unsteady or narrow surfaces; tumble"),
    HEAL("Heal", "Exploration", "Use mundane first aid"),
    JUMP("Jump", "Exploration", "Leap vertical and horizontal distances"),
    LIFT("Lift", "Exploration", "Pick up, carry, and throw heavy objects"),
    NAVIGATE("Navigate", "Exploration", "Read a map and travel without becoming lost"),
    RIDE("Ride", "Exploration", "Ride and control a nonsapient mount, such as a horse"),
    SWIM("Swim", "Exploration", "Move through deep liquid"),
    
    // INTERPERSONAL SKILLS
    BRAG("Brag", "Interpersonal", "Impress others with stories of your deeds"),
    EMPATHIZE("Empathize", "Interpersonal", "Relate to someone on a personal level"),
    FLIRT("Flirt", "Interpersonal", "Attract romantic attention from someone"),
    GAMBLE("Gamble", "Interpersonal", "Make bets with others"),
    HANDLE_ANIMALS("Handle Animals", "Interpersonal", "Interact with nonsapient animal wildlife"),
    INTERROGATE("Interrogate", "Interpersonal", "Obtain information from a creature withholding it"),
    INTIMIDATE("Intimidate", "Interpersonal", "Awe or scare a creature"),
    LEAD("Lead", "Interpersonal", "Inspire people to action"),
    LIE("Lie", "Interpersonal", "Convince someone that a falsehood is true"),
    MUSIC("Music", "Interpersonal", "Perform music vocally or with an instrument"),
    PERFORM("Perform", "Interpersonal", "Engage in dance, oratory, acting, or some other physical performance"),
    PERSUADE("Persuade", "Interpersonal", "Convince someone to agree with you through use of your charms and grace"),
    READ_PERSON("Read Person", "Interpersonal", "Read the emotions and body language of other creatures"),
    
    // INTRIGUE SKILLS
    ALERTNESS("Alertness", "Intrigue", "Intuitively sense the details of your surroundings"),
    CONCEAL_OBJECT("Conceal Object", "Intrigue", "Hide an object on your person or in your environment"),
    DISGUISE("Disguise", "Intrigue", "Change your appearance to look like a different person"),
    EAVESDROP("Eavesdrop", "Intrigue", "Actively listen to something that is hard to hear, such as a whispered conversation through a door"),
    ESCAPE_ARTIST("Escape Artist", "Intrigue", "Escape from bonds such as rope or manacles"),
    HIDE("Hide", "Intrigue", "Conceal yourself from others' observation"),
    PICK_LOCK("Pick Lock", "Intrigue", "Open a lock without using the key"),
    PICK_POCKET("Pick Pocket", "Intrigue", "Steal an item that another person wears or carries without them noticing"),
    SABOTAGE("Sabotage", "Intrigue", "Disable a mechanical device such as a trap"),
    SEARCH("Search", "Intrigue", "Actively search an environment for important details and items"),
    SNEAK("Sneak", "Intrigue", "Move silently"),
    TRACK("Track", "Intrigue", "Follow a trail that another creature has left behind"),
    
    // LORE SKILLS
    CRIMINAL_UNDERWORLD("Criminal Underworld", "Lore", "Knowing about criminal organizations, their crimes, their relationships, and their leaders"),
    CULTURE("Culture", "Lore", "Knowing about a culture's customs, folktales, and taboos"),
    HISTORY("History", "Lore", "Knowing about significant past events"),
    MAGIC("Magic", "Lore", "Knowing about magical places, spells, rituals, items, and phenomena"),
    MONSTERS("Monsters", "Lore", "Knowing monster ecology, strengths, and weaknesses"),
    NATURE("Nature", "Lore", "Knowing about natural flora, fauna, and weather"),
    PSIONICS("Psionics", "Lore", "Knowing about psionic places, spells, rituals, items, and phenomena"),
    RELIGION("Religion", "Lore", "Knowing about religious mythology, practices, and rituals"),
    RUMORS("Rumors", "Lore", "Knowing gossip, legends, and uncertain truths"),
    SOCIETY("Society", "Lore", "Knowing noble etiquette and the leadership and power dynamics of noble families"),
    STRATEGY("Strategy", "Lore", "Knowing about battle tactics and logistics"),
    TIMESCAPE("Timescape", "Lore", "Knowing about the many worlds of the timescape");
    
    private final String displayName;
    private final String skillGroup;
    private final String description;
    
    Skill(String displayName, String skillGroup, String description) {
        this.displayName = displayName;
        this.skillGroup = skillGroup;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getSkillGroup() {
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