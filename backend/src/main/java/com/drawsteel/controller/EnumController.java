package com.drawsteel.controller;

import com.drawsteel.model.enums.Weapon;
import com.drawsteel.model.enums.Armor;
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
}
