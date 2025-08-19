package com.drawsteel.repository;

import com.drawsteel.model.Ability;
import com.drawsteel.model.enums.AbilityType;
import com.drawsteel.model.enums.Area;
import com.drawsteel.model.enums.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, UUID> {
    
    Optional<Ability> findByName(String name);
    
    List<Ability> findByType(AbilityType type);
    
    List<Ability> findByHeroic(Boolean heroic);
    
    List<Ability> findBySignature(Boolean signature);
    
    List<Ability> findByArea(Area area);
    
    @Query("SELECT a FROM Ability a JOIN a.keywords k WHERE k = :keyword")
    List<Ability> findByKeyword(@Param("keyword") String keyword);
    
    @Query("SELECT a FROM Ability a JOIN a.conditions c WHERE c = :condition")
    List<Ability> findByCondition(@Param("condition") Condition condition);
    
    @Query("SELECT a FROM Ability a WHERE a.name LIKE %:searchTerm% OR a.description LIKE %:searchTerm%")
    List<Ability> searchByNameOrDescription(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT a FROM Ability a WHERE a.heroic = true AND a.signature = true")
    List<Ability> findHeroicSignatureAbilities();
    
    @Query("SELECT a FROM Ability a WHERE a.heroic = true OR a.signature = true")
    List<Ability> findSpecialAbilities();
}
