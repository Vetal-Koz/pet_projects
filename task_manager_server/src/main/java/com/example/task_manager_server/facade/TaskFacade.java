package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.TaskRequest;
import com.example.task_manager_server.dto.response.DataTableResponse;
import com.example.task_manager_server.dto.response.TaskResponse;
import com.example.task_manager_server.entity.data.Task;

public interface TaskFacade extends CrudFacade<TaskRequest, TaskResponse> {
}
