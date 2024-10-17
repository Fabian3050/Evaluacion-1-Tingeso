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
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private String rut;
    private int salary;

    @ManyToOne
    @JoinColumn(name = "simulate_id")
    private SimulateEntity simulate;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;
}
