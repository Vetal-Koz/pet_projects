package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.TaskRequest;
import com.example.task_manager_server.dto.responce.DataTableResponse;
import com.example.task_manager_server.dto.responce.TaskResponse;

public interface TaskFacade extends CrudFacade<TaskRequest, TaskResponse>{
    DataTableResponse<TaskResponse> findAllByUserId(Long userId, DataTableRequest request);

}
