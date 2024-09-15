package com.example.user_service.controller;

import com.example.user_service.dto.request.UserRequest;
import com.example.user_service.dto.response.*;
import com.example.user_service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


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

}
