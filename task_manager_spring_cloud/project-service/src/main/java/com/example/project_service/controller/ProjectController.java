package com.example.project_service.controller;


import com.example.project_service.dto.request.AttachUserIdsRequest;
import com.example.project_service.dto.response.ProjectResponse;
import com.example.project_service.dto.response.ResponseContainer;
import com.example.project_service.facade.ProjectFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectFacade projectFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContainer<ProjectResponse>> findProjectById(@Min(1) @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new ResponseContainer<>(projectFacade.findById(id)));
    }

}
