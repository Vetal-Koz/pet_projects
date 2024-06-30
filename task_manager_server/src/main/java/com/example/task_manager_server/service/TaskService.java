package com.example.task_manager_server.service;

import com.example.task_manager_server.entity.data.Task;

public interface TaskService extends CrudService<Task> {
    void create(Task entity, Long userId, Long projectId);
}
