package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.CreditEvaluationEntity;
import com.example.PrestaBanco_Backend.repositories.CreditEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreditEvaluationService {
    @Autowired
    CreditEvaluationRepository creditEvaluationRepository;

    public CreditEvaluationEntity saveCreditEvaluation(CreditEvaluationEntity creditEvaluation){
        return creditEvaluationRepository.save(creditEvaluation);
    }

    public ArrayList<CreditEvaluationEntity> getAllCreditEvaluation(){ return (ArrayList<CreditEvaluationEntity>) creditEvaluationRepository.findAll();}

    public CreditEvaluationEntity getCreditEvaluationById(Long id){
        return creditEvaluationRepository.findById(id).get();
    }

    public CreditEvaluationEntity updateCreditEvaluation(CreditEvaluationEntity creditEvaluation){
        return creditEvaluationRepository.save(creditEvaluation);
    }

    public boolean deleteCreditEvaluation(Long id) throws Exception{
        try{
            creditEvaluationRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
