package com.example.task_manager_server.unit.service;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.exception.NotValidDataException;
import com.example.task_manager_server.repository.data.ProjectRepository;
import com.example.task_manager_server.repository.data.TaskRepository;
import com.example.task_manager_server.repository.user.UserRepository;
import com.example.task_manager_server.service.impl.UserServiceImpl;
import com.example.task_manager_server.util.ExceptionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    final Long id = 1L;
    final String correctEmail = "username@domain.com";
    final String correctPassword = "Password1234";
    final String incorrectEmail = "username@domain";
    final DataTableRequest request = new DataTableRequest(1, 10, "id", "asc");

    @Test
    public void shouldBeCreateUserWhereIdIsNotNull() {
        User user = new User();
        user.setId(id);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_ALREADY_EXIST);
    }

    @Test
    public void shouldBeCreateUserWhereEmailIsNull() {
        User user = new User();

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.EMAIL_IS_NOT_PRESENT);
    }

    @Test
    public void shouldBeCreateUserWhereEmailIsNotValid() {
        User user = new User();
        user.setEmail(incorrectEmail);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.EMAIL_IS_NOT_VALID);
    }

    @Test
    public void shouldBeCreateUserWhereEmailIsExist() {
        User user = new User();
        user.setEmail(correctEmail);
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(true);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.USER_ALREADY_EXIST);
    }

    @Test
    public void shouldBeCreateUserWherePasswordIsNull() {
        User user = new User();
        user.setEmail(correctEmail);
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(false);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.PASSWORD_IS_NOT_PRESENT);
    }

    @Test
    public void shouldBeCreateUserWherePasswordIsNotValid() {
        User user = new User();
        user.setEmail(correctEmail);
        user.setPassword("s");
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(false);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, () -> userService.create(user));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.PASSWORD_IS_NOT_VALID);
    }

    @Test
    public void shouldBeCreateUserWithCorrectEmailAndPassword() {
        User user = new User();
        user.setEmail(correctEmail);
        user.setPassword(correctPassword);
        Mockito.when(userRepository.existsByEmail(correctEmail)).thenReturn(false);

        userService.create(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void shouldBeFindUserWhereIdIsNotExist() {
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(id));

        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.ENTITY_NOT_FOUND);
    }

    @Test
    public void shouldBeFindUserWhereIdIsExist() {
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(new User()));

        User user = userService.findById(id);

        assertThat(user).isInstanceOf(User.class);
    }

    @Test
    public void shouldBeUpdateUser() {
        User user = new User();
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.update(user);

        assertThat(updatedUser).isInstanceOf(User.class);
    }

    @Test
    public void shouldBeFindAllUser() {
        List<User> userList = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        Collection<User> users = userService.findAll();

        assertThat(users).isNotNull();
    }

    @Test
    public void shouldBeFindAllTasksByUserIdWhereUserHasTask() {
        Page<Task> tasks = new PageImpl<>(List.of(new Task()));
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);


        Mockito.when(taskRepository.findAllByUserId(id, pageable)).thenReturn(tasks);
        Page<Task> page = userService.findAllTasksByUserId(id, request);

        Mockito.verify(taskRepository, Mockito.times(1)).findAllByUserId(id, pageable);
        assertThat(page.getContent().get(0)).isInstanceOf(Task.class);
    }

    @Test
    public void shouldBeFindAllProjectsByUserIdWhereUserHasProjects() {
        Page<Project> projectPage = new PageImpl<>(List.of(new Project()));
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);

        Mockito.when(projectRepository.findAllByUserId(id, pageable)).thenReturn(projectPage);
        Page<Project> projects = userService.findAllProjectsByUserId(id, request);

        Mockito.verify(projectRepository, Mockito.times(1)).findAllByUserId(id, pageable);
        assertThat(projects.getContent().get(0)).isInstanceOf(Project.class);
    }

}
