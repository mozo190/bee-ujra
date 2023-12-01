package org.example.repository;

import org.example.domain.Bee;
import org.example.domain.BeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BeeRepository extends JpaRepository<Bee, Long> {
    @Query("SELECT b " +
            "FROM Bee b " +
            "WHERE b.beeType =:beeType")
    List<Bee> findByBeeType(BeeType beeType);
}
