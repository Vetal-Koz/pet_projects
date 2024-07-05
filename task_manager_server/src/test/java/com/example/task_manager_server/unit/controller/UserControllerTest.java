package com.example.task_manager_server.unit.controller;

import com.example.task_manager_server.controller.UserController;
import com.example.task_manager_server.dto.response.*;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.facade.impl.UserFacadeImpl;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserFacadeImpl userFacade;

    @Test
    public void shouldBeFindAllTasksOfUser() throws Exception {
        Page<Task> tasks = new PageImpl<>(List.of(new Task()));
        DataTableResponse<TaskResponse> taskResponse = new DataTableResponse<>(tasks);

        Mockito.when(userFacade.findAllTasksByUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(taskResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/2/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.page").value("1"))
                .andDo(print());
    }

    @Test
    public void shouldBeFindAllProjectsOfUser() throws Exception {
        Page<Project> projects = new PageImpl<>(List.of(new Project()));
        DataTableResponse<ProjectRelatedResponse> projectResponse = new DataTableResponse<>(projects);

        Mockito.when(userFacade.findAllProjectsByUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(projectResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/2/projects")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.page").value("1"))
                .andDo(print());
    }

}
