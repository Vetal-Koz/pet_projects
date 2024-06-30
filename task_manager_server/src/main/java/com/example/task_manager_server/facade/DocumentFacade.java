package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.response.DocumentResponse;
import com.example.task_manager_server.entity.data.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentFacade {
    Document saveAttachment(MultipartFile file, Long taskId);

    void saveFiles(MultipartFile[] files, Long taskId) throws Exception;

    List<DocumentResponse> getAllFiles();

    void downloadToLocalStorage(Long id);

}
