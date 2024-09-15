package com.example.document_service.facade;


import com.example.document_service.dto.request.ApiRequest;
import com.example.document_service.dto.response.ApiResponse;

import java.util.Collection;

public interface CrudFacade<REQ extends ApiRequest, RES extends ApiResponse> {

    RES create(REQ entity);

    RES update(REQ entity, Long id);

    void delete(Long id);

    RES findById(Long id);

    Collection<RES> findAll();

}
