package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional <UserEntity> findById(Long userId);
}
