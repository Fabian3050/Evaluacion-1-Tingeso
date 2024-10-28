package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.CreditEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditEvaluationRepository extends JpaRepository<CreditEvaluationEntity,Long> {
    @Override
    Optional<CreditEvaluationEntity> findById(Long id);
}
