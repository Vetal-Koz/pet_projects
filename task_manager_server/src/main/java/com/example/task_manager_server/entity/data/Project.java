package com.example.task_manager_server.entity.data;

import com.example.task_manager_server.entity.BaseEntity;
import com.example.task_manager_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    private String title;

    @ManyToMany
    @JoinTable(
            name = "proj_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> team;

    @OneToMany(mappedBy = "project", cascade=CascadeType.MERGE)
    private Set<Task> tasks;

    public Project(){
        tasks = new HashSet<>();
    }
}
