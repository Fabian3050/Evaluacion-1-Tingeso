package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.dto.CreditDto;
import com.example.PrestaBanco_Backend.dto.DocumentDto;
import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.DocumentEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DocumentService documentService;
    @Autowired
    UserService userService;


    public Long saveCredit(CreditEntity credit, Long userId){
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.getCredits().add(credit);
            credit.setUser(user);
            credit.setInterestRate((credit.getInterestRate()/12)/100);
            creditRepository.save(credit);
            return credit.getId();
        }else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public List<CreditDto> getAllCredit(){
        List<CreditEntity> credits = creditRepository.findAll();
        return  credits.stream()
                .map(this::convertCreditToDTO)
                .collect(Collectors.toList());
    }

    public CreditEntity getCreditById(Long creditId) {
        return creditRepository.findById(creditId)
                .orElseThrow(() -> new NoSuchElementException("No credit found with ID: " + creditId));
    }

    public List<CreditDto> getAllCreditByUserId(Long userId) {
        UserEntity user = userService.getUSerById(userId);
        return user.getCredits().stream()
                .map(this::convertCreditToDTO)
                .collect(Collectors.toList());
    }

    public int getCreditTotalCost(Long creditId){
        Optional<CreditEntity> credit = creditRepository.findById(creditId);
        int requestedAmount = credit.get().getRequestedAmount();
        int maxTerm = credit.get().getMaxTerm();
        float interestRate = credit.get().getTotalCreditCost();
        float creditLifeInsurance = (float) (requestedAmount * 0.0003);
        int fireInsurance = 20000;
        float commission = (float) (requestedAmount * 0.001);


        double power = Math.pow(1 + interestRate, maxTerm);
        float monthlyPayment = (float) (requestedAmount * (interestRate * power) / (power - 1)) + creditLifeInsurance + fireInsurance;
        int totalCost = Math.round(monthlyPayment * maxTerm + commission);

        return totalCost;
    }

    public String getUserIdByCredit(CreditEntity credit){
        UserEntity user = credit.getUser();
        String rut = user.getRut();
        return rut;
    }

    public CreditEntity updateCredit(CreditEntity credit){
        return creditRepository.save(credit);
    }

    public boolean deleteCredit(Long id) throws Exception{
        try{
            creditRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public CreditDto convertCreditToDTO(CreditEntity credit){
        CreditDto creditDTO = new CreditDto();
        creditDTO.setId(credit.getId());
        creditDTO.setCreditType(credit.getCreditType());
        creditDTO.setMaxTerm(credit.getMaxTerm());
        creditDTO.setInterestRate(credit.getInterestRate());
        creditDTO.setRequestedAmount(credit.getRequestedAmount());
        creditDTO.setApprovedAmount(credit.getApprovedAmount());
        creditDTO.setStatus(credit.getStatus());
        creditDTO.setApplicationDate(credit.getApplicationDate());
        creditDTO.setApprovedRejectionDate(credit.getApprovedRejectionDate());
        creditDTO.setUser(credit.getUser());
        creditDTO.setCreditEvaluation(credit.getCreditEvaluation());

        List<DocumentDto> documentDTOS = new ArrayList<>();
        if (credit.getDocuments() != null) {
            documentDTOS = credit.getDocuments().stream()
                    .map(this::convertDocumentToDTO)
                    .collect(Collectors.toList());
        }
        creditDTO.setDocuments(documentDTOS);

        return creditDTO;
    }

    public DocumentDto convertDocumentToDTO(DocumentEntity document) {
        DocumentDto dto = new DocumentDto();
        dto.setId(document.getId());
        dto.setDocumentName(document.getDocumentName());
        dto.setDocumentType(document.getDocumentType());
        dto.setTypeCreditDocument(document.getTypeCreditDocument());
        return dto;
    }

    public CreditEntity updateStatus(Long id, String status) {
        CreditEntity credit = creditRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el crédito con ID: " + id));

        credit.setStatus(status);
        return creditRepository.save(credit);
    }
}
