package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.ExecutiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutiveRepository extends JpaRepository<ExecutiveEntity,Long> {
}
