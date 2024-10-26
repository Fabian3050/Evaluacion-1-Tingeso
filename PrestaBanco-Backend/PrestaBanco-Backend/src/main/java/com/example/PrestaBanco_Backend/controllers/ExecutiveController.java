package com.example.PrestaBanco_Backend.controllers;

import com.example.PrestaBanco_Backend.entities.ExecutiveEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.services.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/executive")
@CrossOrigin("*")
public class ExecutiveController {
    @Autowired
    ExecutiveService executiveService;

    @GetMapping("/get")
    public ResponseEntity<List<ExecutiveEntity>> ListExecutives(){
        List<ExecutiveEntity> executives = executiveService.getAllExecutive();
        return ResponseEntity.ok(executives);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExecutiveEntity> getExecutiveById(@PathVariable Long id){
        ExecutiveEntity executive = executiveService.getExecutiveById(id);
        return ResponseEntity.ok(executive);
    }

    @PostMapping("/")
    public ResponseEntity<ExecutiveEntity> saveExecutive(@RequestBody ExecutiveEntity executive){
        ExecutiveEntity newExecutive = executiveService.saveExecutive(executive);
        return ResponseEntity.ok(newExecutive);
    }

    @PutMapping("/")
    public ResponseEntity<ExecutiveEntity> updateExecutive(@RequestBody ExecutiveEntity executive){
        ExecutiveEntity updatedExecutive = executiveService.updateExecutive(executive);
        return  ResponseEntity.ok(updatedExecutive);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutiveEntity> deleteExecutive(@PathVariable Long id) throws Exception {
        var isDeleted = executiveService.deleteExecutive(id);
        return ResponseEntity.noContent().build();
    }
}
