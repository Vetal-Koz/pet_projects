package com.example.task_service.entity.data;

import com.example.task_service.entity.BaseEntity;
import com.example.task_service.entity.user.User;
import com.example.task_service.type.TaskType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type", nullable = false)
    private TaskType type;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "accomplish_to")
    private Date accomplishTo;

    @Column(name = "user_id", nullable = false)
    private Long userId;


    @Column(name = "project_id", nullable = false)
    private Long projectId;

    public Task() {
        this.createdAt = new Date();
    }
}
