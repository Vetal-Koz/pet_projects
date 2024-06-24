package com.example.task_manager_server.service;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.user.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService extends CrudService<Project>{
    Page<Project> findAllByUserId(Long userId, DataTableRequest request);

    List<User> findAllByNotInTeamProject(Long projectId);

    void attachUsersToProject(Long projectId, List<Long> userIds);
}
