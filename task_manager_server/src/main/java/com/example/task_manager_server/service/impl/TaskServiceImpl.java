package com.example.task_manager_server.service.impl;

import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.repository.data.ProjectRepository;
import com.example.task_manager_server.repository.data.TaskRepository;
import com.example.task_manager_server.repository.user.UserRepository;
import com.example.task_manager_server.service.TaskService;
import com.example.task_manager_server.type.TaskType;
import com.example.task_manager_server.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public Task create(Task entity) {
        entity.setType(TaskType.TO_DO);
        return taskRepository.save(entity);
    }

    @Override
    public Task update(Task entity) {
        return taskRepository.save(entity);
    }

    @Override
    @Transactional
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public void create(Task entity, Long userId, Long projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        entity.setProject(project);
        entity.setUser(user);
        taskRepository.save(entity);
    }
}
