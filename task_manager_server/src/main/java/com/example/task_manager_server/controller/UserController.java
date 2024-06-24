package com.example.task_manager_server.controller;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.responce.DataTableResponse;
import com.example.task_manager_server.dto.responce.ProjectResponse;
import com.example.task_manager_server.dto.responce.ResponseContainer;
import com.example.task_manager_server.dto.responce.TaskResponse;
import com.example.task_manager_server.facade.ProjectFacade;
import com.example.task_manager_server.facade.TaskFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")

public class UserController {
    private final TaskFacade taskFacade;
    private final ProjectFacade projectFacade;

    @GetMapping("/{id}/tasks")
    public ResponseEntity<ResponseContainer<DataTableResponse<TaskResponse>>> findAllTaskOfUser (
            @PathVariable("id") Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order){
        DataTableRequest dataTableRequest = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(taskFacade.findAllByUserId(userId, dataTableRequest)));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<ResponseContainer<DataTableResponse<ProjectResponse>>> findAllProjectsOfUser (
            @PathVariable("id") Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order){
        DataTableRequest dataTableRequest = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(projectFacade.findAllByUserId(userId, dataTableRequest)));
    }
}
