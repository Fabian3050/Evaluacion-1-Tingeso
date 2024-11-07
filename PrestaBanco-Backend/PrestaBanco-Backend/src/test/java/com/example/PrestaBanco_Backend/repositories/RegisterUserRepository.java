package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.RegisterUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUserEntity,Long> {
}
