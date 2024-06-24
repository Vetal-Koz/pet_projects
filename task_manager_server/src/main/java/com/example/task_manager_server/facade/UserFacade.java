package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.UserRequest;
import com.example.task_manager_server.dto.responce.UserResponse;

public interface UserFacade extends CrudFacade<UserRequest, UserResponse>{
}
