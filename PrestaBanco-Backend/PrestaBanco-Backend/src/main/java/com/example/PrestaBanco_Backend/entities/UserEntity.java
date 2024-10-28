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
@Table(name = "users")
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
    private String address;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @OneToOne
    @JoinColumn(name = "register_id")
    private RegisterUserEntity registerUser;
}
