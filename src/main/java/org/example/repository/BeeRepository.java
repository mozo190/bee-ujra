package org.example.repository;

import org.example.domain.Bee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeeRepository extends JpaRepository<Bee, Long> {
}
