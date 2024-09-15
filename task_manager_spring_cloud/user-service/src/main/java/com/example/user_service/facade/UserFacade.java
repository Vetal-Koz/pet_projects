package com.example.user_service.facade;


import com.example.user_service.dto.request.UserRequest;
import com.example.user_service.dto.response.UserResponse;

public interface UserFacade extends CrudFacade<UserRequest, UserResponse> {
}
