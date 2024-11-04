package com.example.PrestaBanco_Backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany
    @JoinColumn(name = "credit_id")
    @JsonManagedReference
    private List<CreditEntity> credits = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "register_id")
    private RegisterUserEntity registerUser;
}
