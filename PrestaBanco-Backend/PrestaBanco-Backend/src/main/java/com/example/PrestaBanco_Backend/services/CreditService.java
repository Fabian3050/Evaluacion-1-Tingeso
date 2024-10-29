package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.LoanTypeRepository;
import com.example.PrestaBanco_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    LoanTypeRepository loanTypeRepository;
    @Autowired
    UserRepository userRepository;


    public Long saveCredit(CreditEntity credit, Long userId){
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            credit.setUser(user);
            creditRepository.save(credit);
            return credit.getId();
        }else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public ArrayList<CreditEntity> getAllCredit(){
        return (ArrayList<CreditEntity>) creditRepository.findAll();
    }

    public CreditEntity getCreditById(Long id){
        return creditRepository.findById(id).get();
    }

    public CreditEntity updateCredit(CreditEntity credit){
        return creditRepository.save(credit);
    }

    public boolean deleteCredit(Long id) throws Exception{
        try{
            creditRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
