package com.example.PrestaBanco_Backend.controllers;

import com.example.PrestaBanco_Backend.entities.SimulateEntity;
import com.example.PrestaBanco_Backend.services.SimulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulate")
@CrossOrigin("*")
public class SimulateController {
    @Autowired
    SimulateService simulateService;

    @PostMapping("/")
    public ResponseEntity<SimulateEntity> saveSimulate(@RequestBody SimulateEntity simulate){
        SimulateEntity simul = simulateService.saveSimulate(simulate);
        return ResponseEntity.ok(simul);
    }

    @GetMapping("/getM/{id}")
    public ResponseEntity<SimulateEntity> getSimulateCredit(@PathVariable Long id){
        SimulateEntity simulate = simulateService.getSimulateCredit(id);
        return ResponseEntity.ok(simulate);
    }
}
