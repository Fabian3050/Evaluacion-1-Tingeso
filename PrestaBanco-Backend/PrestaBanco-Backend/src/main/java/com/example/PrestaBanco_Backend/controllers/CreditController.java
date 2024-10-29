package com.example.PrestaBanco_Backend.controllers;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit")
@CrossOrigin("*")
public class CreditController {
    @Autowired
    CreditService creditService;

    @GetMapping("/get")
    public ResponseEntity<List<CreditEntity>> ListCredit(){
        List<CreditEntity> credits = creditService.getAllCredit();
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreditEntity> getCreditById(@PathVariable Long id){
        CreditEntity credit = creditService.getCreditById(id);
        return ResponseEntity.ok(credit);
    }

    @PostMapping("/{userId}")
    public Long saveCredit(@RequestBody CreditEntity credit, @PathVariable Long userId){
        return creditService.saveCredit(credit,userId);
    }

    @PutMapping("/")
    public ResponseEntity<CreditEntity> updateCredit(@RequestBody CreditEntity credit){
        CreditEntity updatedCredit = creditService.updateCredit(credit);
        return  ResponseEntity.ok(updatedCredit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CreditEntity> deleteCredit(@PathVariable Long id) throws Exception {
        var isDeleted = creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
}
