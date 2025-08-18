package com.drawsteel.repository;

import com.drawsteel.model.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TraitRepository extends JpaRepository<Trait, UUID> {
    // Find traits by ancestry
    List<Trait> findByAncestryId(UUID ancestryId);
    
    // Find traits by cost
    List<Trait> findByCost(Integer cost);
    
    // Find traits by signature toggle
    List<Trait> findBySignatureToggle(Boolean signatureToggle);
}
