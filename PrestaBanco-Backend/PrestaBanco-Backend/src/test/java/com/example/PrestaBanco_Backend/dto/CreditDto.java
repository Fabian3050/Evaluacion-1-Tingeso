package com.example.PrestaBanco_Backend.dto;

import com.example.PrestaBanco_Backend.entities.CreditEvaluationEntity;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private Long id;
    private String creditType;
    private int maxTerm;
    private float interestRate;
    private int requestedAmount;
    private int approvedAmount;
    private String status;
    private Date applicationDate;
    private Date approvedRejectionDate;
    private UserEntity user;
    private List<DocumentDto> documents;
    private CreditEvaluationEntity creditEvaluation;
}
