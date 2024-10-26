package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.SimulateEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.SimulateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SimulateService {
    @Autowired
    SimulateRepository simulateRepository;

    public SimulateEntity saveSimulate(SimulateEntity simulate){
        return simulateRepository.save(simulate);
    }

    public ArrayList<SimulateEntity> getAllSimulate(){
        return (ArrayList<SimulateEntity>) simulateRepository.findAll();
    }

    public SimulateEntity getSimulateById(Long id){
        return simulateRepository.findById(id).get();
    }

    public float calculateMonthlyPayment(SimulateEntity simulate) {
        // Extract variables from the simulate object
        float principal = simulate.getP();  // Loan amount
        float monthlyRate = simulate.getR(); // Monthly interest rate
        int paymentPeriod = simulate.getN();   // Number of payments (months)

        // Validate that the values are valid
        if (monthlyRate == 0 || paymentPeriod == 0) {
            throw new IllegalArgumentException("Interest rate and number of payments must be greater than zero.");
        }

        // Calculate the common power
        double power = Math.pow(1 + monthlyRate, paymentPeriod);

        // Apply the formula to calculate the monthly payment
        float monthlyPayment = (float) (principal * (monthlyRate * power) / (power - 1));

        return monthlyPayment;
    }

    //verificar bien el uso de esta funcion, preguntar a chat gpt como realizar potencias en java
    public SimulateEntity getSimulateCredit(Long id){
        SimulateEntity simulate = getSimulateById(id);
        float monthlyFee = this.calculateMonthlyPayment(simulate);
        simulate.setM(monthlyFee);
        return simulate;
    }

}
