package com.example.PrestaBanco_Backend.services;
import com.example.PrestaBanco_Backend.entities.SimulateEntity;
import com.example.PrestaBanco_Backend.repositories.SimulateRepository;
import com.example.PrestaBanco_Backend.services.SimulateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class SimulateServiceTest {
    @Mock
    private SimulateRepository simulateRepository;

    @InjectMocks
    private SimulateService simulateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSimulate() {
        SimulateEntity simulate = new SimulateEntity();

        when(simulateRepository.save(simulate)).thenReturn(simulate);

        SimulateEntity result = simulateService.saveSimulate(simulate);

        assertNotNull(result);
        assertEquals(simulate, result);
        verify(simulateRepository, times(1)).save(simulate);
    }

    @Test
    public void testGetAllSimulate() {
        ArrayList<SimulateEntity> simulations = new ArrayList<>();
        when(simulateRepository.findAll()).thenReturn(simulations);

        ArrayList<SimulateEntity> result = simulateService.getAllSimulate();

        assertEquals(simulations, result);
        verify(simulateRepository, times(1)).findAll();
    }

    @Test
    public void testGetSimulateById() {
        Long id = 1L;
        SimulateEntity simulate = new SimulateEntity();

        when(simulateRepository.findById(id)).thenReturn(Optional.of(simulate));

        SimulateEntity result = simulateService.getSimulateById(id);

        assertNotNull(result);
        assertEquals(simulate, result);
        verify(simulateRepository, times(1)).findById(id);
    }

    @Test
    public void testCalculateMonthlyPayment() {
        SimulateEntity simulate = new SimulateEntity();
        simulate.setP(100000); // Principal amount
        simulate.setR(5);      // Annual interest rate
        simulate.setN(12);     // Payment period in months

        float monthlyPayment = simulateService.calculateMonthlyPayment(simulate);

        assertTrue(monthlyPayment > 0);
    }

    @Test
    public void testGetSimulateCredit() {
        Long id = 1L;
        SimulateEntity simulate = new SimulateEntity();
        simulate.setMonthlyClientIncome(500000);
        simulate.setP(100000); // Principal
        simulate.setR(5);      // Annual interest rate
        simulate.setN(12);     // Payment period

        when(simulateRepository.findById(id)).thenReturn(Optional.of(simulate));

        SimulateEntity result = simulateService.getSimulateCredit(id);

        assertNotNull(result);
        assertEquals(simulate, result);
        assertTrue(result.getM() > 0);
        verify(simulateRepository, times(1)).findById(id);
    }

    @Test
    public void testCalculateMonthlyPaymentAllCost() {
        Long simulateId = 1L;
        SimulateEntity simulate = new SimulateEntity();
        simulate.setP(100000); // Principal amount
        simulate.setM(5000);   // Monthly payment

        when(simulateRepository.findById(simulateId)).thenReturn(Optional.of(simulate));

        SimulateEntity result = simulateService.calculateMonthlyPaymentAllCost(simulateId);

        assertNotNull(result);
        assertTrue(result.getTotalMonthlyPay() > 0);
        verify(simulateRepository, times(1)).findById(simulateId);
    }
}
