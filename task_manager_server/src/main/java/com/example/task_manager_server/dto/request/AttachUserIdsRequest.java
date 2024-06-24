package com.example.task_manager_server.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttachUserIdsRequest extends ApiRequest{
    List<Long> userIds;
}
