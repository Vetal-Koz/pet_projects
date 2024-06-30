package com.example.task_manager_server.service;

import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.user.User;

import java.util.List;

public interface ProjectService extends CrudService<Project> {
    List<User> findAllByNotInTeamProject(Long projectId);

    void attachUsersToProject(Long projectId, List<Long> userIds);
}
