package com.example.user_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskRequest extends ApiRequest {
    private String title;
    private String description;
    private String type;
    private Date accomplishTo;
    private Long userId;
    private Long projectId;

}
