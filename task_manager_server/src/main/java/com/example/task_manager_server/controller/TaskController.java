package com.example.task_manager_server.controller;


import com.example.task_manager_server.dto.request.TaskRequest;
import com.example.task_manager_server.dto.response.ResponseContainer;
import com.example.task_manager_server.facade.TaskFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskFacade taskFacade;

    @PostMapping()
    public ResponseEntity<ResponseContainer<Boolean>> create(@RequestBody TaskRequest taskRequest) {
        taskFacade.create(taskRequest);
        return ResponseEntity.ok(new ResponseContainer<>(true));
    }
}
