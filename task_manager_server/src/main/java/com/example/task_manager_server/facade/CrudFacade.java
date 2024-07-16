package com.example.task_manager_server.facade;

import com.example.task_manager_server.dto.request.ApiRequest;
import com.example.task_manager_server.dto.response.ApiResponse;

import java.util.Collection;

public interface CrudFacade<REQ extends ApiRequest, RES extends ApiResponse> {

    RES create(REQ entity);

    RES update(REQ entity, Long id);

    void delete(Long id);

    RES findById(Long id);

    Collection<RES> findAll();

}
