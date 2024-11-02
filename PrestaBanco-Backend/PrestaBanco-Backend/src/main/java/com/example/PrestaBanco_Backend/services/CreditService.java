package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.dto.CreditDto;
import com.example.PrestaBanco_Backend.dto.DocumentDto;
import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.DocumentEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.LoanTypeRepository;
import com.example.PrestaBanco_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    LoanTypeRepository loanTypeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DocumentService documentService;


    public Long saveCredit(CreditEntity credit, Long userId){
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            credit.setUser(user);
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

    public List<CreditDto> getAllCreditByUserId(Long userId){
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return user.getCredits().stream()
                    .map(this::convertCreditToDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
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
        creditDTO.setRequestedAmount(credit.getRequestedAmount());
        creditDTO.setApprovedAmount(credit.getApprovedAmount());
        creditDTO.setMaxTerm(credit.getMaxTerm());
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
}
