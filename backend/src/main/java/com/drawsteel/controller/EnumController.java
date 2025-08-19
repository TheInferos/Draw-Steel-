package com.drawsteel.controller;

import com.drawsteel.model.enums.Weapon;
import com.drawsteel.model.enums.Armor;
import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.model.enums.AbilityKeyword;
import com.drawsteel.model.enums.AbilityScore;
import com.drawsteel.model.enums.Skill;
import com.drawsteel.model.enums.Area;
import com.drawsteel.model.enums.Condition;
import com.drawsteel.model.enums.PerkType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enums")
@CrossOrigin(origins = "*")
public class EnumController {
    
    @GetMapping("/weapons")
    public List<Map<String, Object>> getAllWeapons() {
        return List.of(Weapon.values()).stream()
                .map(weapon -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", weapon.name());
                    map.put("displayName", weapon.getDisplayName());
                    map.put("description", weapon.getDescription());
                    map.put("weaponType", weapon.getWeaponType().name());
                    map.put("isMelee", weapon.isMelee());
                    map.put("isRanged", weapon.isRanged());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/armors")
    public List<Map<String, Object>> getAllArmor() {
        return List.of(Armor.values()).stream()
                .map(armor -> {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", armor.name());
                    map.put("displayName", armor.getDisplayName());
                    map.put("description", armor.getDescription());
                    map.put("isLight", armor.isLight());
                    map.put("isMedium", armor.isMedium());
                    map.put("isHeavy", armor.isHeavy());
                    map.put("isShield", armor.isShield());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/weapons/melee")
    public List<Map<String, Object>> getMeleeWeapons() {
        return List.of(Weapon.values()).stream()
                .filter(Weapon::isMelee)
                .map(weapon -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", weapon.name());
                    map.put("displayName", weapon.getDisplayName());
                    map.put("description", weapon.getDescription());
                    map.put("weaponType", weapon.getWeaponType().name());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/weapons/ranged")
    public List<Map<String, Object>> getRangedWeapons() {
        return List.of(Weapon.values()).stream()
                .filter(Weapon::isRanged)
                .map(weapon -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", weapon.name());
                    map.put("displayName", weapon.getDisplayName());
                    map.put("description", weapon.getDescription());
                    map.put("weaponType", weapon.getWeaponType().name());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/ability-types")
    public List<Map<String, Object>> getAllAbilityTypes() {
        return List.of(AbilityType.values()).stream()
                .map(type -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", type.name());
                    map.put("displayName", type.getDisplayName());
                    map.put("description", type.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/ability-keywords")
    public List<Map<String, Object>> getAllAbilityKeywords() {
        return List.of(AbilityKeyword.values()).stream()
                .map(keyword -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", keyword.name());
                    map.put("displayName", keyword.getDisplayName());
                    map.put("description", keyword.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/areas")
    public List<Map<String, Object>> getAllAreas() {
        return List.of(Area.values()).stream()
                .map(area -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", area.name());
                    map.put("displayName", area.getDisplayName());
                    map.put("description", area.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/conditions")
    public List<Map<String, Object>> getAllConditions() {
        return List.of(Condition.values()).stream()
                .map(condition -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", condition.name());
                    map.put("displayName", condition.getDisplayName());
                    map.put("description", condition.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/perk-types")
    public List<Map<String, Object>> getAllPerkTypes() {
        return List.of(PerkType.values()).stream()
                .map(type -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", type.name());
                    map.put("displayName", type.getDisplayName());
                    map.put("description", type.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/ability-scores")
    public List<Map<String, Object>> getAllAbilityScores() {
        return List.of(AbilityScore.values()).stream()
                .map(score -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", score.name());
                    map.put("displayName", score.getDisplayName());
                    map.put("description", score.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/skills")
    public List<Map<String, Object>> getAllSkills() {
        return List.of(Skill.values()).stream()
                .map(skill -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", skill.name());
                    map.put("displayName", skill.getDisplayName());
                    map.put("description", skill.getDescription());
                    map.put("skillGroup", skill.getSkillGroup());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
