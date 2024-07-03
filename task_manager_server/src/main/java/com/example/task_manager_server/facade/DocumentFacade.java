package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.response.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentFacade {
    DocumentResponse saveAttachment(MultipartFile file, Long taskId);
    List<DocumentResponse> saveFiles(MultipartFile[] files, Long taskId);
    List<DocumentResponse> getAllFiles();
    void downloadToLocalStorage(Long id);

}
