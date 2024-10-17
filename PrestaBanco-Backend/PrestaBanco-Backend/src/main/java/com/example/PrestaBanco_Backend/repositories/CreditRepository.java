package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity,Long> {
}
