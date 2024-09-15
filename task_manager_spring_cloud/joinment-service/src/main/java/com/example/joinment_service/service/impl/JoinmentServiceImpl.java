package com.example.joinment_service.service.impl;

import com.example.joinment_service.dto.response.ProjectResponse;
import com.example.joinment_service.dto.response.ResponseContainer;
import com.example.joinment_service.entity.Joinment;
import com.example.joinment_service.feignClients.ProjectFeignClient;
import com.example.joinment_service.service.JoinmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinmentServiceImpl implements JoinmentService {

    private final ProjectFeignClient projectFeignClient;
    @Override
    public void addUser(Joinment entity) {
        System.out.println();
        ResponseContainer<ProjectResponse> project = projectFeignClient.findProjectById(1L).getBody();
        System.out.println(project.getData());
    }

    @Override
    public void deleteUser(Joinment entity) {

    }

    @Override
    public List<Long> findUserIdsByProjectId(Long projectId) {
        return null;
    }

    @Override
    public List<Long> findProjectIdsByUserId(Long userId) {
        return null;
    }
}
