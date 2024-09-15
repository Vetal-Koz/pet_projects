package com.example.document_service.service;

import com.example.document_service.entity.data.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService extends CrudService<Document> {
    Document saveAttachment(MultipartFile file, Long taskId) ;

    List<Document> saveFiles(MultipartFile[] files, Long taskId);

    void downloadToLocalStorage(Long id);

    List<Document> getAllFiles();
}