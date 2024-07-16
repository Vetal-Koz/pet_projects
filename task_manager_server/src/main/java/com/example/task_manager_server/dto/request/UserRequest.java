package com.example.task_manager_server.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends ApiRequest {
    private String email;
    private String password;
    private String username;
}
