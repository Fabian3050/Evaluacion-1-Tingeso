package com.example.PrestaBanco_Backend.controllers;

import com.example.PrestaBanco_Backend.entities.CreditEvaluationEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.services.CreditEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/creditEvaluation")
@CrossOrigin("*")
public class CreditEvaluationController {
    @Autowired
    CreditEvaluationService creditEvaluationService;

    @GetMapping("/get")
    public ResponseEntity<List<CreditEvaluationEntity>> ListEvaluation(){
        List<CreditEvaluationEntity> creditEvaluations = creditEvaluationService.getAllCreditEvaluation();
        return ResponseEntity.ok(creditEvaluations);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreditEvaluationEntity> getCreditEvaluation(@PathVariable Long id){
        CreditEvaluationEntity creditEvaluation = creditEvaluationService.getCreditEvaluationById(id);
        return ResponseEntity.ok(creditEvaluation);
    }

    @PostMapping("/")
    public ResponseEntity<CreditEvaluationEntity> saveCreditEvaluation(@RequestBody CreditEvaluationEntity creditEvaluation){
        CreditEvaluationEntity newCreditEvaluation = creditEvaluationService.saveCreditEvaluation(creditEvaluation);
        return ResponseEntity.ok(newCreditEvaluation);
    }

    @PutMapping("/")
    public ResponseEntity<CreditEvaluationEntity> updateCreditEvaluation(@RequestBody CreditEvaluationEntity creditEvaluation){
        CreditEvaluationEntity updatedCreditEvaluation = creditEvaluationService.updateCreditEvaluation(creditEvaluation);
        return  ResponseEntity.ok(updatedCreditEvaluation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CreditEvaluationEntity> deleteUser(@PathVariable Long id) throws Exception {
        var isDeleted = creditEvaluationService.deleteCreditEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
