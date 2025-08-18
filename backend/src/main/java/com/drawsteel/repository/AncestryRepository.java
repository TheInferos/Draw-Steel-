package com.drawsteel.repository;

import com.drawsteel.model.Ancestry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AncestryRepository extends JpaRepository<Ancestry, UUID> {
    // Custom query methods can be added here
    // Spring Data JPA will automatically implement basic CRUD operations
}
