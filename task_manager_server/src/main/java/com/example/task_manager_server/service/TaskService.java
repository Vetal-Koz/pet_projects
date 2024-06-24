package com.example.task_manager_server.service;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Task;
import org.springframework.data.domain.Page;

public interface TaskService extends CrudService<Task>{
    Page<Task> findAllByUserId(Long userId, DataTableRequest request);

    void create(Task entity, Long userId, Long projectId);
}
