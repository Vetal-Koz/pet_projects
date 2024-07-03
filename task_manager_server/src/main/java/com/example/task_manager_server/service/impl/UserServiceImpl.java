package com.example.task_manager_server.service.impl;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.exception.NotValidDataException;
import com.example.task_manager_server.repository.data.ProjectRepository;
import com.example.task_manager_server.repository.data.TaskRepository;
import com.example.task_manager_server.repository.user.UserRepository;
import com.example.task_manager_server.service.UserService;
import com.example.task_manager_server.util.ExceptionUtil;
import com.example.task_manager_server.util.ValidatorsUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public User create(User entity) {
        checkCorrectUser(entity);
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Task> findAllTasksByUserId(Long userId, DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return taskRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Project> findAllProjectsByUserId(Long userId, DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return projectRepository.findAllByUserId(userId, pageable);
    }

    private void checkCorrectUser(User user){
        checkIdIsNotNull(user.getId());
        checkEmailIsNull(user.getEmail());
        checkEmailIsNotValid(user.getEmail());
        checkExistByEmail(user.getEmail());
        checkPasswordIsNull(user.getPassword());
        checkPasswordIsNotValid(user.getPassword());
    }

    private void checkIdIsNotNull(final Long id){
        if (id != null){
            throw new NotValidDataException(ExceptionUtil.USER_ALREADY_EXIST);
        }
    }

    private void checkEmailIsNull(final String email){
        if (email == null){
            throw new NotValidDataException(ExceptionUtil.EMAIL_IS_NOT_PRESENT);
        }
    }

    private void checkEmailIsNotValid(final String email){
        if (!email.matches(ValidatorsUtil.EMAIL_REGEX)){
            throw new NotValidDataException(ExceptionUtil.EMAIL_IS_NOT_VALID);
        }
    }

    private void checkExistByEmail(final String email){
        if (userRepository.existsByEmail(email)){
            throw new NotValidDataException(ExceptionUtil.USER_ALREADY_EXIST);
        }
    }

    private void checkPasswordIsNull(final String password){
        if (password == null){
            throw new NotValidDataException(ExceptionUtil.PASSWORD_IS_NOT_PRESENT);
        }
    }

    private void checkPasswordIsNotValid(final String password){
        if (!password.matches(ValidatorsUtil.PASSWORD_REGEX)){
            throw new NotValidDataException(ExceptionUtil.PASSWORD_IS_NOT_VALID);
        }
    }
}
