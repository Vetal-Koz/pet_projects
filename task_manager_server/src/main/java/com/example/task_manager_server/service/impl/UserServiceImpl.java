package com.example.task_manager_server.service.impl;

import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.repository.user.UserRepository;
import com.example.task_manager_server.service.UserService;
import com.example.task_manager_server.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void update(User entity) {
        userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
