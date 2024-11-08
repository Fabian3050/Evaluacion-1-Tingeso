package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserEntity> getUsers() {return (ArrayList<UserEntity>) userRepository.findAll();}

    public UserEntity getUSerById(Long id){return userRepository.findById(id).get();}

    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user){
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) throws Exception {
        try{
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
