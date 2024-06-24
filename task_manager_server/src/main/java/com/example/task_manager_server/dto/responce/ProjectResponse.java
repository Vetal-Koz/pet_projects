package com.example.task_manager_server.dto.responce;

import com.example.task_manager_server.entity.data.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectResponse extends ApiResponse{
    private String title;
    private List<UserResponse> team;
    private List<TaskResponse> tasks;

    public ProjectResponse(Project project){
        setId(project.getId());
        this.title = project.getTitle();
        this.team = project.getTeam().stream().map(UserResponse::new).toList();
        this.tasks = project.getTasks().stream().map(TaskResponse::new).toList();
    }
}
