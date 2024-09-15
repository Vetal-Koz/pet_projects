package com.example.task_service.service.impl;

import com.example.task_service.entity.data.Project;
import com.example.task_service.entity.data.Task;
import com.example.task_service.entity.user.User;
import com.example.task_service.exception.EntityNotFoundException;
import com.example.task_service.exception.NotValidDataException;
import com.example.task_service.repository.ProjectRepository;
import com.example.task_service.repository.TaskRepository;
import com.example.task_service.repository.UserRepository;
import com.example.task_service.service.TaskService;
import com.example.task_service.type.TaskType;
import com.example.task_service.util.ExceptionUtil;
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
    public Task create(Task entity, Long userId, Long projectId) {
        checkCorrectTask(entity);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        entity.setProject(project);
        entity.setUser(user);
        return taskRepository.save(entity);
    }

    private void checkCorrectTask(Task entity){
        checkIdIsNotNull(entity.getId());
    }
    private void checkIdIsNotNull(Long id){
        if (id != null){
            throw new NotValidDataException(ExceptionUtil.TASK_ALREADY_EXIST);
        }
    }
}
