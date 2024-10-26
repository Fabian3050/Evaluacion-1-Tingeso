package com.example.PrestaBanco_Backend.entities;


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
@Table(name = "credit")
public class CreditEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int creditType;
    private int maxTerm;
    private float interestRate;
    private int maximumFinancingAmount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "executive_id")
    private ExecutiveEntity executive;

    @OneToOne
    @JoinColumn(name = "simulate_id")
    private SimulateEntity simulate;

    @OneToOne
    @JoinColumn(name = "creditEvaluation_id")
    private CreditEntity creditEvaluation;
}
