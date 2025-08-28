package com.drawsteel.repository;

import com.drawsteel.model.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface TraitRepository extends JpaRepository<Trait, UUID> {
    
    List<Trait> findByCost(Integer cost);
    
    List<Trait> findBySignatureToggle(Boolean signatureToggle);
    
    Optional<Trait> findByName(String name);
    
    List<Trait> findByAbilitiesIsNotNullAndAbilitiesIsNotEmpty();
    
    List<Trait> findByAbilitiesIsEmpty();
}
