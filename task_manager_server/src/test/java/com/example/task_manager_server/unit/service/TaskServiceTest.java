package com.example.task_manager_server.unit.service;

import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.exception.NotValidDataException;
import com.example.task_manager_server.repository.data.TaskRepository;
import com.example.task_manager_server.service.impl.TaskServiceImpl;
import com.example.task_manager_server.util.ExceptionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    TaskServiceImpl taskService;

    @Mock
    TaskRepository taskRepository;

    final Long taskId = 1L;
    final Long userId = 1L;
    final Long projectId = 1L;

    @Test
    public void shouldBeCreateTaskWhereIdIsNotNull(){
        Task task = new Task();
        task.setId(taskId);

        Exception thrown = Assertions.assertThrows(NotValidDataException.class, ()-> taskService.create(task, userId, projectId));

        assertThat(thrown).isInstanceOf(NotValidDataException.class);
        assertThat(thrown.getMessage()).isEqualTo(ExceptionUtil.TASK_ALREADY_EXIST);
    }
}
