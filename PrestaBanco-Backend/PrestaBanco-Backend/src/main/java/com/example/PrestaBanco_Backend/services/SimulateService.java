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

    public float getMonthlyFee(SimulateEntity simulate){
        float m = (float) (simulate.getP() * ((simulate.getR() * Math.pow((1 + simulate.getR()), simulate.getN()))/((Math.pow((1 + simulate.getR()), simulate.getN())) - 1)));
        return m ;
    }

    public float calcularCuotaMensual(SimulateEntity simulate) {
        // Extraer las variables del objeto simulate
        float principal = simulate.getP();  // Monto del préstamo
        float monthlyRate = simulate.getR(); // Tasa de interés mensual
        int paymentPeriod = simulate.getN();   // Número de pagos (meses)

        // Validar que los valores sean válidos
        if (monthlyRate == 0 || paymentPeriod == 0) {
            throw new IllegalArgumentException("La tasa de interés y el número de pagos deben ser mayores a cero.");
        }

        // Cálculo de la potencia común
        double potencia = Math.pow(1 + monthlyRate, paymentPeriod);

        // Aplicar la fórmula para calcular la cuota mensual
        float cuotaMensual = (float) (principal * (monthlyRate * potencia) / (potencia - 1));

        return cuotaMensual;
    }

    //verificar bien el uso de esta funcion, preguntar a chat gpt como realizar potencias en java
    public SimulateEntity getSimulateCredit(Long id){
        SimulateEntity simulate = getSimulateById(id);
        float monthlyFee = this.calcularCuotaMensual(simulate);
        simulate.setM(monthlyFee);
        return simulate;
    }

}
