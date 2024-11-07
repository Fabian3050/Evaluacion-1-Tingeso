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
@Table(name = "simulate")
public class SimulateEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int m;
    private int p; //loan amount
    private float r; //monthly interest rate (anual rate/12/100)
    private int n; //total payment period
    private int totalPriceHome;
    private int monthlyClientIncome;
    private String creditType;
    private String message;
    private int totalCreditCost;
    private int totalMonthlyPay;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}