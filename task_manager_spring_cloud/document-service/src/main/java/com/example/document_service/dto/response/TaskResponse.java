package com.example.document_service.dto.response;


import com.example.document_service.entity.data.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskResponse extends ApiResponse {
    private String title;

    private String description;

    private String type;

    private Date createdAt;

    private Date accomplishTo;

    UserResponse user;

    List<DocumentResponse> documents;

    public TaskResponse(Task task) {
        setId(task.getId());
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.type = task.getType().getType();
        this.createdAt = task.getCreatedAt();
        this.accomplishTo = task.getAccomplishTo();
        this.user = new UserResponse(task.getUser());
        this.documents = task.getDocuments().stream().map(DocumentResponse::new).toList();
    }
}
