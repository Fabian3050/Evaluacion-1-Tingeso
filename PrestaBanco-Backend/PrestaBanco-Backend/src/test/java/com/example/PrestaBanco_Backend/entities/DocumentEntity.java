package com.example.PrestaBanco_Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "document")
public class DocumentEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeCreditDocument;

    private String documentName;

    private String documentType;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @JsonIgnore
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;
}
