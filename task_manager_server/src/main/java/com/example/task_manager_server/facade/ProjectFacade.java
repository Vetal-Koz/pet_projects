package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.AttachUserIdsRequest;
import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.ProjectRequest;
import com.example.task_manager_server.dto.response.DataTableResponse;
import com.example.task_manager_server.dto.response.ProjectRelatedResponse;
import com.example.task_manager_server.dto.response.ProjectResponse;
import com.example.task_manager_server.dto.response.UserResponse;

import java.util.List;

public interface ProjectFacade extends CrudFacade<ProjectRequest, ProjectResponse> {
    List<UserResponse> findAllByNotInTeamProject(Long projectId);

    void attachUsersToProject(Long projectId, AttachUserIdsRequest userIdsRequest);
}
