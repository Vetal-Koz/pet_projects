package com.example.task_manager_server.controller;

import com.example.task_manager_server.dto.request.AttachUserIdsRequest;
import com.example.task_manager_server.dto.response.ProjectResponse;
import com.example.task_manager_server.dto.response.ResponseContainer;
import com.example.task_manager_server.dto.response.UserResponse;
import com.example.task_manager_server.facade.ProjectFacade;
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

    @GetMapping("{id}/team/attach")
    public ResponseEntity<ResponseContainer<List<UserResponse>>> findNotAttachedToTeam(@Min(0) @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new ResponseContainer<>(projectFacade.findAllByNotInTeamProject(id)));
    }

    @PostMapping("{id}/team/attach")
    public ResponseEntity<ResponseContainer<Boolean>> attachToTeam(@Min(0) @PathVariable(name = "id") Long id,
                                                                   @RequestBody AttachUserIdsRequest userIds) {
        projectFacade.attachUsersToProject(id, userIds);
        return ResponseEntity.ok(new ResponseContainer<>(true));
    }
}
