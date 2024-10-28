package com.example.PrestaBanco_Backend.controllers;

import com.example.PrestaBanco_Backend.entities.DocumentEntity;
import com.example.PrestaBanco_Backend.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/document")
@CrossOrigin("*")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @PostMapping("/{id}")
    public ResponseEntity<String> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("doc") String doc,
            @PathVariable Long id
    ) throws IOException {
        DocumentEntity savedDocument = documentService.saveDocument(file, doc, id);
        return ResponseEntity.ok("Document uploaded successfully. ID: " + savedDocument.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) throws FileNotFoundException {
        DocumentEntity fileEntity = documentService.getFile(id).orElseThrow(FileNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, fileEntity.getDocumentType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getDocumentName()+"\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentEntity>> getAllDocuments() {
        List<DocumentEntity> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }
}
