package com.example.joinment_service.service;

import com.example.joinment_service.entity.Joinment;

import java.util.List;

public interface JoinmentService {

    void addUser(Joinment entity);

    void deleteUser(Joinment entity);

    List<Long> findUserIdsByProjectId(Long projectId);

    List<Long> findProjectIdsByUserId(Long userId);
}
