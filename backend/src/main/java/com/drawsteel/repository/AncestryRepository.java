package com.drawsteel.repository;

import com.drawsteel.model.Ancestry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface AncestryRepository extends JpaRepository<Ancestry, UUID> {
    Optional<Ancestry> findByName(String name);
}
