package com.drawsteel.repository;

import com.drawsteel.model.Perk;
import com.drawsteel.model.enums.PerkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerkRepository extends JpaRepository<Perk, UUID> {
    
    Optional<Perk> findByName(String name);
    
    List<Perk> findByType(PerkType type);
    
    @Query("SELECT p FROM Perk p WHERE p.name LIKE %:searchTerm% OR p.description LIKE %:searchTerm%")
    List<Perk> searchByNameOrDescription(@Param("searchTerm") String searchTerm);
    
    List<Perk> findByAbilityIsNotNull();
    
    List<Perk> findByAbilityIsNull();
    
    @Query("SELECT p FROM Perk p WHERE p.type = :type AND p.ability IS NOT NULL")
    List<Perk> findByTypeWithAbility(@Param("type") PerkType type);
    
    @Query("SELECT p FROM Perk p WHERE p.type = :type AND p.ability IS NULL")
    List<Perk> findByTypeWithoutAbility(@Param("type") PerkType type);
}
