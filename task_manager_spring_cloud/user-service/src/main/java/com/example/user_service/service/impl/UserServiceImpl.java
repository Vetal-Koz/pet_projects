package com.example.user_service.service.impl;


import com.example.user_service.entity.user.User;
import com.example.user_service.exception.EntityNotFoundException;
import com.example.user_service.exception.NotValidDataException;

import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import com.example.user_service.util.ExceptionUtil;
import com.example.user_service.util.ValidatorsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
