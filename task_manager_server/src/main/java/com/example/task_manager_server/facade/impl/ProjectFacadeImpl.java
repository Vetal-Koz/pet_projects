package com.example.task_manager_server.facade.impl;

import com.example.task_manager_server.dto.request.AttachUserIdsRequest;
import com.example.task_manager_server.dto.request.ProjectRequest;
import com.example.task_manager_server.dto.response.ProjectResponse;
import com.example.task_manager_server.dto.response.UserResponse;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.facade.ProjectFacade;
import com.example.task_manager_server.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectFacadeImpl implements ProjectFacade {
    private final ProjectService projectService;

    @Override
    public ProjectResponse create(ProjectRequest entity) {
        Project project = new Project();
        BeanUtils.copyProperties(entity, project);
        return new ProjectResponse(projectService.create(project));
    }

    @Override
    public ProjectResponse update(ProjectRequest entity, Long id) {
        Project project = projectService.findById(id);
        BeanUtils.copyProperties(entity, project);
        return new ProjectResponse(projectService.update(project));
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
    public List<UserResponse> findAllByNotInTeamProject(Long projectId) {
        return projectService.findAllByNotInTeamProject(projectId).stream().map(UserResponse::new).toList();
    }

    @Override
    public void attachUsersToProject(Long projectId, AttachUserIdsRequest userIdsRequest) {
        projectService.attachUsersToProject(projectId, userIdsRequest.getUserIds());
    }
}
