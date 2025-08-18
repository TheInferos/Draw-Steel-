package com.drawsteel.repository;

import com.drawsteel.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CultureRepository extends JpaRepository<Culture, UUID> {
}
