package com.example.task_manager_server.service;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Document;
import org.springframework.data.domain.Page;

public interface DocumentService extends CrudService<Document>{
    Page<Document> findAllByTaskId(Long taskId, DataTableRequest dataTableRequest);

}
