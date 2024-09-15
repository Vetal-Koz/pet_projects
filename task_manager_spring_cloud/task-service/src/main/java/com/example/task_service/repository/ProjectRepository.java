package com.example.task_service.repository;

import com.example.task_service.entity.data.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
