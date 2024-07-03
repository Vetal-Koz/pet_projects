package com.example.task_manager_server.facade.impl;

import com.example.task_manager_server.dto.response.DocumentResponse;
import com.example.task_manager_server.entity.data.Document;
import com.example.task_manager_server.facade.DocumentFacade;
import com.example.task_manager_server.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFacadeImpl implements DocumentFacade {
    private final DocumentService documentService;

    @Override
    public DocumentResponse saveAttachment(MultipartFile file, Long taskId) {
        return new DocumentResponse(documentService.saveAttachment(file, taskId));
    }

    @Override
    public List<DocumentResponse> saveFiles(MultipartFile[] files, Long taskId) {
        List<Document> documents = documentService.saveFiles(files, taskId);
        return documents.stream().map(DocumentResponse::new).toList();
    }

    @Override
    public List<DocumentResponse> getAllFiles() {
        return documentService.getAllFiles().stream().map(DocumentResponse::new).toList();
    }

    @Override
    public void downloadToLocalStorage(Long id) {
        documentService.downloadToLocalStorage(id);
    }
}
