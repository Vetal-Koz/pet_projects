package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.TaskRequest;
import com.example.task_manager_server.dto.response.TaskResponse;

public interface TaskFacade extends CrudFacade<TaskRequest, TaskResponse> {
}
