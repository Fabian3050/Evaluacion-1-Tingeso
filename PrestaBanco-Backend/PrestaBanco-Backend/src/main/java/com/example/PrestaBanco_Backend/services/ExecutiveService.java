package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.ExecutiveEntity;
import com.example.PrestaBanco_Backend.repositories.ExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExecutiveService {
    @Autowired
    ExecutiveRepository executiveRepository;

    public ArrayList<ExecutiveEntity> getAllExecutive(){
        return (ArrayList<ExecutiveEntity>) executiveRepository.findAll();
    }

    public ExecutiveEntity getExecutiveById(Long id){
        return executiveRepository.findById(id).get();
    }

    public ExecutiveEntity saveExecutive(ExecutiveEntity executive){
        return executiveRepository.save(executive);
    }

    public ExecutiveEntity updateExecutive(ExecutiveEntity executive){
        return executiveRepository.save(executive);
    }

    public Boolean deleteExecutive(Long id) throws Exception{
        try{
            executiveRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
