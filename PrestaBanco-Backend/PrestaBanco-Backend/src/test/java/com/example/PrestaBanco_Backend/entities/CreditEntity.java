package com.example.PrestaBanco_Backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
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


    private String creditType;
    private int maxTerm;
    private float interestRate;
    private int requestedAmount;
    private int approvedAmount;
    private String status;
    private Date applicationDate = new Date() ;
    private Date approvedRejectionDate;
    private int totalCreditCost;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<DocumentEntity> documents = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "simulate_id")
    private SimulateEntity simulate;

    @OneToOne
    @JoinColumn(name = "creditEvaluation_id")
    @JsonManagedReference
    private CreditEvaluationEntity creditEvaluation;
}
