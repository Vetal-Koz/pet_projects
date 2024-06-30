package com.example.task_manager_server.service;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.entity.user.User;
import org.springframework.data.domain.Page;

public interface UserService extends CrudService<User> {

    Page<Task> findAllTasksByUserId(Long userId, DataTableRequest request);

    Page<Project> findAllProjectsByUserId(Long userId, DataTableRequest request);

}
