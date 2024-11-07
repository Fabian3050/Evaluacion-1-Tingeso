package com.example.PrestaBanco_Backend.repositories;

import com.example.PrestaBanco_Backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Override
    Optional <UserEntity> findById(Long userId);
}
