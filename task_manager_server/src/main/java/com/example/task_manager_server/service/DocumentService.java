package com.example.task_manager_server.service;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Document;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService extends CrudService<Document> {
    Page<Document> findAllByTaskId(Long taskId, DataTableRequest dataTableRequest);

    Document saveAttachment(MultipartFile file, Long taskId) throws Exception;

    void saveFiles(MultipartFile[] files, Long taskId) throws Exception;

    void downloadToLocalStorage(Long id);

    List<Document> getAllFiles();
}
