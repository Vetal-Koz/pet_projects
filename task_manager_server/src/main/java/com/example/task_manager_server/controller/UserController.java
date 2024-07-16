package com.example.task_manager_server.controller;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.dto.request.UserRequest;
import com.example.task_manager_server.dto.response.*;
import com.example.task_manager_server.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")

public class UserController {
    private final UserFacade userFacade;

    @GetMapping()
    public ResponseEntity<ResponseContainer<Collection<UserResponse>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseContainer<>(userFacade.findAll()));
    }

    @PostMapping()
    public ResponseEntity<ResponseContainer<UserResponse>> create(@RequestBody UserRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseContainer<>(userFacade.create(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseContainer<UserResponse>> update(
                @PathVariable Long id,
                @RequestBody UserRequest entity){
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.update(entity, id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContainer<UserResponse>> findById(@PathVariable Long id){
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.findById(id)));
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<ResponseContainer<DataTableResponse<TaskResponse>>> findAllTaskOfUser(
            @PathVariable("id") Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        DataTableRequest dataTableRequest = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.findAllTasksByUserId(userId, dataTableRequest)));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<ResponseContainer<DataTableResponse<ProjectRelatedResponse>>> findAllProjectsOfUser(
            @PathVariable("id") Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        DataTableRequest dataTableRequest = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.findAllProjectsByUserId(userId, dataTableRequest)));
    }

}
