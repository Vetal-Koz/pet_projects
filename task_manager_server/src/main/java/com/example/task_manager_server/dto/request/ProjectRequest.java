package com.example.task_manager_server.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest extends ApiRequest{
    private String title;

}
