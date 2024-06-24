package com.example.task_manager_server.facade.impl;

import com.example.task_manager_server.dto.request.AttachUserIdsRequest;
import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.ProjectRequest;
import com.example.task_manager_server.dto.responce.DataTableResponse;
import com.example.task_manager_server.dto.responce.ProjectResponse;
import com.example.task_manager_server.dto.responce.UserResponse;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.facade.ProjectFacade;
import com.example.task_manager_server.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectFacadeImpl implements ProjectFacade {
    private final ProjectService projectService;

    @Override
    public void create(ProjectRequest entity) {

    }

    @Override
    public void update(ProjectRequest entity, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProjectResponse findById(Long id) {
        return new ProjectResponse(projectService.findById(id));
    }

    @Override
    public Collection<ProjectResponse> findAll() {
        return null;
    }

    @Override
    public DataTableResponse<ProjectResponse> findAllByUserId(Long userId, DataTableRequest request) {
        Page<Project> page = projectService.findAllByUserId(userId, request);
        DataTableResponse<ProjectResponse> dataTableResponse = new DataTableResponse<>(page);
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<ProjectResponse> projectResponseList = page
                .stream()
                .map(ProjectResponse::new)
                .toList();
        dataTableResponse.setItems(projectResponseList);
        return dataTableResponse;
    }

    @Override
    public List<UserResponse> findAllByNotInTeamProject(Long projectId) {
        return projectService.findAllByNotInTeamProject(projectId).stream().map(UserResponse::new).toList();
    }

    @Override
    public void attachUsersToProject(Long projectId, AttachUserIdsRequest userIdsRequest) {
        projectService.attachUsersToProject(projectId, userIdsRequest.getUserIds());
    }
}
