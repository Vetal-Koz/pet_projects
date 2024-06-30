package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.UserRequest;
import com.example.task_manager_server.dto.response.*;

public interface UserFacade extends CrudFacade<UserRequest, UserResponse> {
    DataTableResponse<TaskResponse> findAllTasksByUserId(Long userId, DataTableRequest request);
    DataTableResponse<ProjectRelatedResponse> findAllProjectsByUserId(Long userId, DataTableRequest request);


}
