package com.example.task_manager_server.integration.controller;

import com.example.task_manager_server.dto.request.UserRequest;
import com.example.task_manager_server.dto.response.DataTableResponse;
import com.example.task_manager_server.dto.response.ResponseContainer;
import com.example.task_manager_server.dto.response.UserResponse;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RequiredArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

//    private final UserFacade userFacade;

    @LocalServerPort
    private int port;

    private final String URL = "/api/users";

    private final String email = "username@gmail.com";
    private final String password = "Test1234";
    private final String username = "userName";
    private String createURLWithPort() {
        return "http://localhost:" + port + URL;
    }

    @Test
    @Order(1)
    public void shouldBeCreateUser(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        userRequest.setUsername(username);

        ResponseEntity<ResponseContainer<DataTableResponse<UserResponse>>> response =
                testRestTemplate.exchange(
                        createURLWithPort(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ResponseContainer<DataTableResponse<UserResponse>>>(){}
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseContainer<DataTableResponse<UserResponse>> dataTableResponse = response.getBody();

        assertThat(dataTableResponse).isNotNull();
    }
}
