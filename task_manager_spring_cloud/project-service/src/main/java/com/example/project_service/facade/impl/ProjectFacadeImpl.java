package com.example.project_service.facade.impl;


import com.example.project_service.dto.request.AttachUserIdsRequest;
import com.example.project_service.dto.request.ProjectRequest;
import com.example.project_service.dto.response.ProjectResponse;
import com.example.project_service.entity.data.Project;
import com.example.project_service.facade.ProjectFacade;
import com.example.project_service.service.ProjectService;
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

}
