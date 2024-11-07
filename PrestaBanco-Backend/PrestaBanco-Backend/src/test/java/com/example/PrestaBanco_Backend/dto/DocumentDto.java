package com.example.PrestaBanco_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private Long id;
    private String typeCreditDocument;
    private String documentName;
    private String documentType;
}
