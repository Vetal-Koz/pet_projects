package com.example.task_manager_server.service;

import com.example.task_manager_server.entity.data.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService extends CrudService<Document> {
    Document saveAttachment(MultipartFile file, Long taskId) ;

    List<Document> saveFiles(MultipartFile[] files, Long taskId);

    void downloadToLocalStorage(Long id);

    List<Document> getAllFiles();
}
