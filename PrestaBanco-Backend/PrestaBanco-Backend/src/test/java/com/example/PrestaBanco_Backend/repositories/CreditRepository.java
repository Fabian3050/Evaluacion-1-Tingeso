package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity,Long> {

    Optional <CreditEntity> findById(Long creditId);
}
