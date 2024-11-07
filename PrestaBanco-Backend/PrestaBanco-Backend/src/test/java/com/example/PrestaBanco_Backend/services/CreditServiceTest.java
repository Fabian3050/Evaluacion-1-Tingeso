package com.example.PrestaBanco_Backend.services;
import com.example.PrestaBanco_Backend.dto.CreditDto;
import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.UserRepository;
import com.example.PrestaBanco_Backend.services.CreditService;
import com.example.PrestaBanco_Backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditServiceTest {
    @Mock
    private CreditRepository creditRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private CreditService creditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCredit_UserNotFound() {
        Long userId = 1L;
        CreditEntity credit = new CreditEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> creditService.saveCredit(credit, userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetAllCredit() {
        List<CreditEntity> credits = new ArrayList<>();
        when(creditRepository.findAll()).thenReturn(credits);

        List<CreditDto> result = creditService.getAllCredit();

        assertNotNull(result);
        verify(creditRepository, times(1)).findAll();
    }

    @Test
    public void testGetCreditById_NotFound() {
        Long creditId = 1L;

        when(creditRepository.findById(creditId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> creditService.getCreditById(creditId));
        verify(creditRepository, times(1)).findById(creditId);
    }

    @Test
    public void testGetCreditTotalCost() {
        Long creditId = 1L;
        CreditEntity credit = new CreditEntity();
        credit.setRequestedAmount(1000);
        credit.setMaxTerm(12);
        credit.setTotalCreditCost((int) 0.05f);

        when(creditRepository.findById(creditId)).thenReturn(Optional.of(credit));

        int totalCost = creditService.getCreditTotalCost(creditId);

        assertTrue(totalCost > 0);
        verify(creditRepository, times(1)).findById(creditId);
    }

    @Test
    public void testUpdateCredit() {
        CreditEntity credit = new CreditEntity();

        when(creditRepository.save(credit)).thenReturn(credit);

        CreditEntity result = creditService.updateCredit(credit);

        assertNotNull(result);
        assertEquals(credit, result);
        verify(creditRepository, times(1)).save(credit);
    }

    @Test
    public void testDeleteCredit() throws Exception {
        Long id = 1L;

        doNothing().when(creditRepository).deleteById(id);

        boolean result = creditService.deleteCredit(id);

        assertTrue(result);
        verify(creditRepository, times(1)).deleteById(id);
    }
}
