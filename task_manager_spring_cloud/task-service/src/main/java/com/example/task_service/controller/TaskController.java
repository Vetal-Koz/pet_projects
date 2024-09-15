package com.example.task_service.controller;

import com.example.task_service.dto.request.TaskRequest;
import com.example.task_service.dto.response.ResponseContainer;
import com.example.task_service.facade.TaskFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
