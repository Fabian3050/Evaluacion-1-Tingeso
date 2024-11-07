package com.example.PrestaBanco_Backend.services;
import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.CreditEvaluationEntity;
import com.example.PrestaBanco_Backend.repositories.CreditEvaluationRepository;
import com.example.PrestaBanco_Backend.services.CreditEvaluationService;
import com.example.PrestaBanco_Backend.services.CreditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class CreditEvaluationServiceTest {
    @Mock
    private CreditEvaluationRepository creditEvaluationRepository;

    @Mock
    private CreditService creditService;

    @InjectMocks
    private CreditEvaluationService creditEvaluationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCreditEvaluation() {
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity();
        CreditEntity credit = new CreditEntity();
        Long creditId = 1L;

        when(creditService.getCreditById(creditId)).thenReturn(credit);
        when(creditEvaluationRepository.save(creditEvaluation)).thenReturn(creditEvaluation);

        CreditEvaluationEntity result = creditEvaluationService.saveCreditEvaluation(creditEvaluation, creditId);

        assertNotNull(result);
        verify(creditService, times(1)).getCreditById(creditId);
        verify(creditEvaluationRepository, times(1)).save(creditEvaluation);
    }

    @Test
    public void testGetAllCreditEvaluation() {
        ArrayList<CreditEvaluationEntity> evaluations = new ArrayList<>();
        when(creditEvaluationRepository.findAll()).thenReturn(evaluations);

        ArrayList<CreditEvaluationEntity> result = creditEvaluationService.getAllCreditEvaluation();

        assertEquals(evaluations, result);
        verify(creditEvaluationRepository, times(1)).findAll();
    }

    @Test
    public void testGetCreditEvaluationById() {
        Long id = 1L;
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity();

        when(creditEvaluationRepository.findById(id)).thenReturn(Optional.of(creditEvaluation));

        CreditEvaluationEntity result = creditEvaluationService.getCreditEvaluationById(id);

        assertNotNull(result);
        assertEquals(creditEvaluation, result);
        verify(creditEvaluationRepository, times(1)).findById(id);
    }

    @Test
    public void testGetCreditEvaluationByCreditId() {
        Long creditId = 1L;
        CreditEntity credit = new CreditEntity();
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity();
        credit.setCreditEvaluation(creditEvaluation);

        when(creditService.getCreditById(creditId)).thenReturn(credit);

        CreditEvaluationEntity result = creditEvaluationService.getCreditEvaluationByCreditId(creditId);

        assertNotNull(result);
        assertEquals(creditEvaluation, result);
        verify(creditService, times(1)).getCreditById(creditId);
    }

    @Test
    public void testUpdateCreditEvaluation() {
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity();

        when(creditEvaluationRepository.save(creditEvaluation)).thenReturn(creditEvaluation);

        CreditEvaluationEntity result = creditEvaluationService.updateCreditEvaluation(creditEvaluation);

        assertNotNull(result);
        assertEquals(creditEvaluation, result);
        verify(creditEvaluationRepository, times(1)).save(creditEvaluation);
    }

    @Test
    public void testDeleteCreditEvaluation() throws Exception {
        Long id = 1L;

        doNothing().when(creditEvaluationRepository).deleteById(id);

        boolean result = creditEvaluationService.deleteCreditEvaluation(id);

        assertTrue(result);
        verify(creditEvaluationRepository, times(1)).deleteById(id);
    }
}
