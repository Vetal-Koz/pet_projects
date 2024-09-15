package com.example.document_service.facade;

import com.example.document_service.dto.response.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentFacade {
    DocumentResponse saveAttachment(MultipartFile file, Long taskId);
    List<DocumentResponse> saveFiles(MultipartFile[] files, Long taskId);
    List<DocumentResponse> getAllFiles();
    void downloadToLocalStorage(Long id);

}
