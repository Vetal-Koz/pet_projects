package com.example.task_manager_server.entity.data;

import com.example.task_manager_server.entity.BaseEntity;
import com.example.task_manager_server.entity.data.Document;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.type.TaskType;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "task")
    Set<Document> documents;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    Project project;

    public Task() {
        this.createdAt = new Date();
        this.documents = new HashSet<>();
    }
}
