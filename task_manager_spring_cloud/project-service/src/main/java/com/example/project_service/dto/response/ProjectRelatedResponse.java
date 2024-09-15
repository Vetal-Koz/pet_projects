package com.example.project_service.dto.response;

import com.example.project_service.entity.data.Project;
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
