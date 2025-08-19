package com.drawsteel.repository;

import com.drawsteel.model.Complication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComplicationRepository extends JpaRepository<Complication, UUID> {
    
    Optional<Complication> findByName(String name);
    
    @Query("SELECT c FROM Complication c WHERE c.name LIKE %:searchTerm% OR c.description LIKE %:searchTerm%")
    List<Complication> searchByNameOrDescription(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT c FROM Complication c WHERE c.benefit LIKE %:searchTerm% OR c.drawback LIKE %:searchTerm%")
    List<Complication> searchByBenefitOrDrawback(@Param("searchTerm") String searchTerm);
    
    List<Complication> findByBenefitIsNotNull();
    
    List<Complication> findByDrawbackIsNotNull();
    
    List<Complication> findByBenefitIsNullAndDrawbackIsNull();
    
    List<Complication> findByCombinedBenefitDrawbackIsNotNull();
    
    @Query("SELECT c FROM Complication c WHERE c.combinedBenefitDrawback LIKE %:searchTerm%")
    List<Complication> searchByCombinedBenefitDrawback(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT c FROM Complication c WHERE c.benefit LIKE %:searchTerm% OR c.drawback LIKE %:searchTerm% OR c.combinedBenefitDrawback LIKE %:searchTerm%")
    List<Complication> searchByAllBenefitDrawbackFields(@Param("searchTerm") String searchTerm);
}
