package com.example.project_service.dto.response;

import com.example.project_service.entity.data.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectResponse extends ApiResponse {
    private String title;

    public ProjectResponse(Project project) {
        setId(project.getId());
        this.title = project.getTitle();
    }
}
