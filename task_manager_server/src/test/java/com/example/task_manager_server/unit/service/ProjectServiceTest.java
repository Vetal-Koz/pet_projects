package com.example.task_manager_server.unit.service;

import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.exception.NotValidDataException;
import com.example.task_manager_server.repository.data.ProjectRepository;
import com.example.task_manager_server.repository.user.UserRepository;
import com.example.task_manager_server.service.impl.ProjectServiceImpl;
import com.example.task_manager_server.util.ExceptionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProjectServiceTest {

    @InjectMocks
    public ProjectServiceImpl projectService;

    @Mock
    public ProjectRepository projectRepository;

    @Mock
    public UserRepository userRepository;

    final Long id = 1L;
    @Test
    public void shouldBeCreateProjectWhereIdIsNotNull(){
        Project project = new Project();
        project.setId(id);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, ()->projectService.create(project));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.PROJECT_ALREADY_EXIST);
    }

    @Test
    public void shouldBeCreateProjectWhereProjectIsCorrect(){
        Project project = new Project();

        projectService.create(project);

        Mockito.verify(projectRepository, Mockito.times(1)).save(project);
    }

    @Test
    public void shouldBeFindProjectWhereIdIsNotExist(){
        Mockito.when(projectRepository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, ()->projectService.findById(id));

        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldBeFindProjectWhereIdIsExist(){
        Mockito.when(projectRepository.findById(id)).thenReturn(Optional.of(new Project()));

        Project project = projectService.findById(id);

        assertThat(project).isNotNull();
        assertThat(project).isInstanceOf(Project.class);
    }

    @Test
    public void shouldBeAttachUserToProjectWhereProjectIdIsNull(){
        List<Long> userIds = new ArrayList<>();
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, ()->projectService.attachUsersToProject(id, userIds));

        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldBeAttachUserToProjectWhereProjectIdIsExist(){
        List<Long> userIds = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Mockito.when(projectRepository.findById(id)).thenReturn(Optional.of(new Project()));
        Mockito.when(userRepository.findAllByIdIn(userIds)).thenReturn(users);

        projectService.attachUsersToProject(id, userIds);

        Mockito.verify(userRepository, Mockito.times(1)).findAllByIdIn(userIds);
    }
}
