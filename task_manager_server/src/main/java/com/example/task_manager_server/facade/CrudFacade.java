package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.ApiRequest;
import com.example.task_manager_server.dto.responce.ApiResponse;

import java.util.Collection;

public interface CrudFacade <REQ extends ApiRequest, RES extends ApiResponse> {

    void create(REQ entity);
    void update(REQ entity, Long id);

    void delete(Long id);
    RES findById(Long id);
    Collection<RES> findAll();

}
