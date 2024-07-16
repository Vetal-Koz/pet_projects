package com.example.task_manager_server.facade.impl;


import com.example.task_manager_server.dto.request.TaskRequest;
import com.example.task_manager_server.dto.response.TaskResponse;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.facade.TaskFacade;
import com.example.task_manager_server.service.TaskService;
import com.example.task_manager_server.type.TaskType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    @Override
    public TaskResponse create(TaskRequest entity) {
        Task task = new Task();
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setAccomplishTo(entity.getAccomplishTo());
        return new TaskResponse(
                taskService.create(task, entity.getUserId(), entity.getProjectId())
        );
    }

    @Override
    public TaskResponse update(TaskRequest entity, Long id) {
        Task task = taskService.findById(id);
        BeanUtils.copyProperties(entity, task);
        return new TaskResponse(taskService.update(task));
    }

    @Override
    public void delete(Long id) {
        taskService.delete(id);
    }


    @Override
    @Transactional
    public TaskResponse findById(Long id) {
        return new TaskResponse(taskService.findById(id));
    }

    @Override
    public Collection<TaskResponse> findAll() {
        return null;
    }


}
