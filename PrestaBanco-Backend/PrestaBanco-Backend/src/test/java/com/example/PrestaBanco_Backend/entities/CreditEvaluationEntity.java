package com.example.PrestaBanco_Backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_evaluation")
public class CreditEvaluationEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ratioFeeIncome;
    private Boolean creditHistory;
    private Boolean jobSeniority;
    private Boolean ratioDebtIncome;
    private Boolean maximumFinancingAmount;
    private Boolean applicantAge;
    private Boolean savingCapacity;

    @OneToOne
    @JoinColumn(name = "credit_id")
    @JsonBackReference
    private CreditEntity credit;
}
