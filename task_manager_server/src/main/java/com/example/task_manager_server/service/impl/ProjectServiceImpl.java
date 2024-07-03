package com.example.task_manager_server.service.impl;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.repository.data.ProjectRepository;
import com.example.task_manager_server.repository.user.UserRepository;
import com.example.task_manager_server.service.ProjectService;
import com.example.task_manager_server.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    @Override
    public Project create(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    public Project update(Project entity) {
       return projectRepository.save(entity);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }


    @Override
    public List<User> findAllByNotInTeamProject(Long projectId) {
        return userRepository.findAllByNotInTeamProject(projectId);
    }

    @Override
    public void attachUsersToProject(Long projectId, List<Long> userIds) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        List<User> users = userRepository.findAllByIdIn(userIds);
        Set<User> attachedUsers = project.getTeam();
        attachedUsers.addAll(users);
        projectRepository.save(project);
    }
}
