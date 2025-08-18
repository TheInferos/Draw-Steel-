package com.drawsteel.repository;

import com.drawsteel.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, UUID> {
    Optional<Career> findByName(String name);
}
