package com.example.task_manager_server.dto.response;

import com.example.task_manager_server.entity.data.Project;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectRelatedResponse extends ApiResponse {
    private String title;

    public ProjectRelatedResponse(Project project) {
        setId(project.getId());
        this.title = project.getTitle();
    }
}
