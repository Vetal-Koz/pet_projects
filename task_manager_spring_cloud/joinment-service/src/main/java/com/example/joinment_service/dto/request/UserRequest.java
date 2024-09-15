package com.example.joinment_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends ApiRequest {
    private String email;
    private String password;
    private String username;
}
