package com.example.PrestaBanco_Backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit")
public class CreditEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LoanType_id", nullable = false)
    private LoanTypeEntity loanType;

    private int creditType; // 1 o 2 o 3 o 4
    private int maxTerm;
    private float interestRate;
    private int maximumFinancingAmount;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<DocumentEntity> documents = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "simulate_id")
    private SimulateEntity simulate;

    @OneToOne
    @JoinColumn(name = "creditEvaluation_id")
    private CreditEntity creditEvaluation;
}
