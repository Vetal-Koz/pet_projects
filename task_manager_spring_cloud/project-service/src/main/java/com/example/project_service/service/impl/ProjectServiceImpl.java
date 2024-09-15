package com.example.project_service.service.impl;



import com.example.project_service.entity.data.Project;
import com.example.project_service.exception.EntityNotFoundException;
import com.example.project_service.exception.NotValidDataException;
import com.example.project_service.repository.ProjectRepository;
import com.example.project_service.service.ProjectService;
import com.example.project_service.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project create(Project entity) {
        checkProjectIsCorrect(entity);
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


    private void checkProjectIsCorrect(Project project){
        checkIdIsNotNull(project.getId());
    }
    private void checkIdIsNotNull(Long id){
        if (id != null){
            throw new NotValidDataException(ExceptionUtil.PROJECT_ALREADY_EXIST);
        }
    }
}
