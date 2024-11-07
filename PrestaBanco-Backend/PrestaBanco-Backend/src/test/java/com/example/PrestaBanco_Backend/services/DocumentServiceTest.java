package com.example.PrestaBanco_Backend.services;
import com.example.PrestaBanco_Backend.dto.DocumentDto;
import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.DocumentEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.DocumentRepository;
import com.example.PrestaBanco_Backend.services.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private CreditRepository creditRepository;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private DocumentService documentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDocument_CreditNotFound() throws IOException {
        Long creditId = 1L;

        when(creditRepository.findById(creditId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> documentService.saveDocument(file, "type", creditId));
        verify(creditRepository, times(1)).findById(creditId);
    }

    @Test
    public void testGetAllDocuments() {
        List<DocumentEntity> documents = List.of(new DocumentEntity());

        when(documentRepository.findAll()).thenReturn(documents);

        List<DocumentDto> result = documentService.getAllDocuments();

        assertEquals(documents.size(), result.size());
        verify(documentRepository, times(1)).findAll();
    }
}
