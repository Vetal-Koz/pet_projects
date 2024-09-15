package com.example.task_service.service;

import com.example.task_service.entity.data.Task;

public interface TaskService extends CrudService<Task> {
    Task create(Task entity, Long userId, Long projectId);
}
