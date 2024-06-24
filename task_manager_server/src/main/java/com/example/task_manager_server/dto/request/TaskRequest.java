package com.example.task_manager_server.dto.request;

import com.example.task_manager_server.dto.responce.UserResponse;

import java.util.Date;

public class TaskRequest extends ApiRequest{
    private String title;
    private String description;
    private Date accomplishTo;
    private Long userId;

}
