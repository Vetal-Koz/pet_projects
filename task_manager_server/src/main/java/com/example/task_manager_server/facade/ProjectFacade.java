package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.AttachUserIdsRequest;
import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.ProjectRequest;
import com.example.task_manager_server.dto.responce.DataTableResponse;
import com.example.task_manager_server.dto.responce.ProjectResponse;
import com.example.task_manager_server.dto.responce.TaskResponse;
import com.example.task_manager_server.dto.responce.UserResponse;
import com.example.task_manager_server.entity.user.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectFacade extends CrudFacade<ProjectRequest,ProjectResponse> {
    DataTableResponse<ProjectResponse> findAllByUserId(Long userId, DataTableRequest request);

    List<UserResponse> findAllByNotInTeamProject(Long projectId);

    void attachUsersToProject(Long projectId, AttachUserIdsRequest userIdsRequest);
}
