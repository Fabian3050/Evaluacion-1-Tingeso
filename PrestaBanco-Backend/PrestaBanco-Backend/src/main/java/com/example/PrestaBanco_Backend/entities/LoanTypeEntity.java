package com.example.PrestaBanco_Backend.entities;

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
@Table(name = "loan_type")
public class LoanTypeEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCredit;

    @OneToMany
    @JoinColumn(name = "documents")
    private List<DocumentEntity> documents =  new ArrayList<>();
}
