package com.example.task_manager_server.facade.impl;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.UserRequest;
import com.example.task_manager_server.dto.response.*;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.facade.UserFacade;
import com.example.task_manager_server.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    @Override
    @Transactional
    public DataTableResponse<TaskResponse> findAllTasksByUserId(Long userId, DataTableRequest request) {
        Page<Task> page = userService.findAllTasksByUserId(userId, request);
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

    @Override
    public void create(UserRequest entity) {

    }

    @Override
    public void update(UserRequest entity, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public Collection<UserResponse> findAll() {
        return null;
    }

    @Override
    public DataTableResponse<ProjectRelatedResponse> findAllProjectsByUserId(Long userId, DataTableRequest request) {
        Page<Project> page = userService.findAllProjectsByUserId(userId, request);
        DataTableResponse<ProjectRelatedResponse> dataTableResponse = new DataTableResponse<>(page);
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<ProjectRelatedResponse> projectResponseList = page
                .stream()
                .map(ProjectRelatedResponse::new)
                .toList();
        dataTableResponse.setItems(projectResponseList);
        return dataTableResponse;
    }
}
