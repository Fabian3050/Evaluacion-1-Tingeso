package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.CreditEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditEvaluationRepository extends JpaRepository<CreditEvaluationEntity,Long> {
}
