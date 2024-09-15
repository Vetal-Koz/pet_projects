package com.example.project_service.facade;


import com.example.project_service.dto.request.AttachUserIdsRequest;
import com.example.project_service.dto.request.ProjectRequest;
import com.example.project_service.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectFacade extends CrudFacade<ProjectRequest, ProjectResponse> {
}
