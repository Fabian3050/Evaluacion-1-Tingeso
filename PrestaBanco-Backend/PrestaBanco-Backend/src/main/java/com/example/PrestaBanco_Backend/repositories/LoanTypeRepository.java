package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.LoanTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanTypeEntity,Long> {
    @Override
    Optional<LoanTypeEntity> findById(Long id);
}
