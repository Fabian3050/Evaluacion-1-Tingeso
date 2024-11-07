package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.SimulateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimulateRepository extends JpaRepository<SimulateEntity,Long> {
    Optional<SimulateEntity> findById(Long simulateId);
}
