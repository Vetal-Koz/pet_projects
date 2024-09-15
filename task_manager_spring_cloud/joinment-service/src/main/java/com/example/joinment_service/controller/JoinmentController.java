package com.example.joinment_service.controller;

import com.example.joinment_service.service.JoinmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/joinments")
@RequiredArgsConstructor
public class JoinmentController {

    private final JoinmentService joinmentService;
    @PostMapping()
    public ResponseEntity<Void> create() {
        joinmentService.addUser(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
