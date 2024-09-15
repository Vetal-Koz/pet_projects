package com.example.task_service.entity.user;

import com.example.task_service.entity.BaseEntity;
import com.example.task_service.entity.data.Project;
import com.example.task_service.entity.data.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @OneToMany(mappedBy = "user", cascade= CascadeType.MERGE)
    Set<Task> tasks;

    @ManyToMany(mappedBy = "team")
    Set<Project> projects;
    public User(){
        this.tasks = new HashSet<>();
    }
}
