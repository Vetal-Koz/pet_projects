package com.example.task_manager_server.service.impl;
import com.example.task_manager_server.dto.request.DataTableRequest;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public void create(Task entity) {
        entity.setType(TaskType.TO_DO);
        taskRepository.save(entity);
    }

    @Override
    public void update(Task entity) {
        taskRepository.save(entity);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(()->new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
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
    public Page<Task> findAllByUserId(Long userId, DataTableRequest request) {
        Sort sort = Sort.by(
                    request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                    request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return taskRepository.findAllByUserId(userId, pageable);
    }

    @Override
    @Transactional
    public void create(Task entity, Long userId, Long projectId) {
        entity.setType(TaskType.TO_DO);

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        entity.setProject(project);
        entity.setUser(user);
        taskRepository.save(entity);
    }
}
