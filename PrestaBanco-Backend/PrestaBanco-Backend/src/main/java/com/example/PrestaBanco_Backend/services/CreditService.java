package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    LoanTypeRepository loanTypeRepository;
    @Autowired
    UserService userService;


    public CreditEntity saveCredit(CreditEntity credit, Long userId){
        UserEntity user = userService.getUSerById(userId);
        credit.setUser(user);
        return creditRepository.save(credit);

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
