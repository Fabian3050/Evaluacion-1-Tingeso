package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.DocumentEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    CreditRepository creditRepository;


    public DocumentEntity saveDocument(MultipartFile file, String typeCredit, Long credit_id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<CreditEntity> creditOptional = creditRepository.findById(credit_id);

        if (creditOptional.isPresent()) {
            CreditEntity credit = creditOptional.get();
            DocumentEntity document = DocumentEntity.builder()
                    .documentName(fileName)
                    .documentType(file.getContentType())
                    .data(file.getBytes())
                    .typeCreditDocument(typeCredit)
                    .credit(credit)
                    .build();

            credit.getDocuments().add(document);
            return documentRepository.save(document);
        }

        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Solo se pueden subir archivos en formato pdf");
        }

        throw new RuntimeException("Credit not found with id: " + credit_id);
    }

    @Transactional
    public Optional<DocumentEntity> getFile(Long id) throws FileNotFoundException {
        Optional<DocumentEntity> file = documentRepository.findById(id);
        if(file.isPresent()){
            return file;
        }
        throw new FileNotFoundException();
    }

    // Método para obtener todos los documentos como DTOs
    public List<DocumentEntity> getAllDocuments() {
        return documentRepository.findAll();
    }

}
