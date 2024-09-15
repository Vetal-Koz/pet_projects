package com.example.joinment_service.feignClients;

import com.example.joinment_service.dto.response.ProjectResponse;
import com.example.joinment_service.dto.response.ResponseContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway", path = "/api/projects")
public interface ProjectFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<ResponseContainer<ProjectResponse>> findProjectById(@PathVariable(name = "id") Long id);
}
