package com.example.task_manager_server.facade.impl;


import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.TaskRequest;
import com.example.task_manager_server.dto.responce.DataTableResponse;
import com.example.task_manager_server.dto.responce.TaskResponse;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.facade.TaskFacade;
import com.example.task_manager_server.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    @Override
    public void create(TaskRequest entity) {

    }

    @Override
    public void update(TaskRequest entity, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public TaskResponse findById(Long id) {
        return null;
    }

    @Override
    public Collection<TaskResponse> findAll() {
        return null;
    }

    @Override
    public DataTableResponse<TaskResponse> findAllByUserId(Long userId, DataTableRequest request) {
        Page<Task> page = taskService.findAllByUserId(userId, request);
        DataTableResponse<TaskResponse> dataTableResponse = new DataTableResponse<>(page);
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<TaskResponse> taskResponseList = page.getContent()
                .stream()
                .map(TaskResponse::new)
                .toList();
        dataTableResponse.setItems(taskResponseList);
        return dataTableResponse;
    }
}
