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

    private float m;
    private int p; //loan amount
    private float r; //monthly interest rate (anual rate/12/100)
    private int n; //total payment period

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
