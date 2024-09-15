package com.example.user_service.facade.impl;

import com.example.user_service.dto.request.UserRequest;
import com.example.user_service.dto.response.UserResponse;

import com.example.user_service.entity.user.User;
import com.example.user_service.facade.UserFacade;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    @Override
    public UserResponse create(UserRequest entity) {
        User user = new User();
        BeanUtils.copyProperties(entity, user);
        return new UserResponse(userService.create(user));
    }

    @Override
    public UserResponse update(UserRequest entity, Long id) {
        User user = userService.findById(id);
        BeanUtils.copyProperties(entity, user);
        return new UserResponse(userService.update(user));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public Collection<UserResponse> findAll() {
        return null;
    }

}
