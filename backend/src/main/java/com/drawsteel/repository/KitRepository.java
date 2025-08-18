package com.drawsteel.repository;

import com.drawsteel.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface KitRepository extends JpaRepository<Kit, UUID> {
    Optional<Kit> findByName(String name);
}
