package com.drawsteel.model.enums;

public enum Weapon {
    BOW("Bow", "Bows cover any weapon used to fire an arrow or bolt projectile, including crossbows, longbows, and shortbows. This weapon group also includes weapons that hurl bullets, stones, darts, or small spears, including slings and atlatls.", WeaponType.RANGED),
    
    ENSNARING_WEAPON("Ensnaring Weapon", "Ensnaring weapons include bolas, nets, and other weapons made to capture an enemy and hold them in place.", WeaponType.RANGED),
    
    LIGHT_WEAPON("Light Weapon", "Light weapons are one-handed melee weapons that can be used to make several strikes in rapid succession. Many such weapons can be thrown or used as an off-hand defensive weapon. Daggers, shortswords, rapiers, handaxes, and throwing hammers are typical light weapons. If your kit uses a light weapon, you can wield two light weapons at a time.", WeaponType.MELEE),
    
    MEDIUM_WEAPON("Medium Weapon", "Medium weapons are one-handed melee weapons that can be carried into battle while leaving one hand free, allowing you to use that hand to hold a shield or implement. Battleaxes, clubs, longswords, and warhammers are medium weapons.", WeaponType.MELEE),
    
    HEAVY_WEAPON("Heavy Weapon", "Heavy weapons are two-handed melee weapons with weighty bladed or bludgeoning heads, made to seriously harm or kill enemies in a single mighty blow. Greatswords, greataxes, mauls, and morningstars are all examples of heavy weapons.", WeaponType.MELEE),
    
    POLEARM("Polearm", "Polearms are two-handed melee weapons with long hafts that increase the wielder's reach. They include glaives, halberds, longspears, and quarterstaffs.", WeaponType.MELEE),
    
    UNARMED_STRIKES("Unarmed Strikes", "Any kit that uses unarmed strikes allows you to use your body as a weapon. Punches, kicks, eye gouges, and the like are your forte.", WeaponType.MELEE),
    
    WHIP("Whip", "Whip weapons include the standard whip, spiked chains, flails, and any similarly long and flexible melee weapon.", WeaponType.MELEE);
    
    private final String displayName;
    private final String description;
    private final WeaponType weaponType;
    
    Weapon(String displayName, String description, WeaponType weaponType) {
        this.displayName = displayName;
        this.description = description;
        this.weaponType = weaponType;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public WeaponType getWeaponType() {
        return weaponType;
    }
    
    public boolean isMelee() {
        return weaponType == WeaponType.MELEE;
    }
    
    public boolean isRanged() {
        return weaponType == WeaponType.RANGED;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
    
    public enum WeaponType {
        MELEE, RANGED
    }
}
