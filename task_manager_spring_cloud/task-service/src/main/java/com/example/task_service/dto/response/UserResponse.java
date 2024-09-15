package com.example.task_service.dto.response;

import com.example.task_service.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends ApiResponse {
    private String email;
    private String username;

    public UserResponse(User user) {
        setId(user.getId());
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
