package com.example.user_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttachUserIdsRequest extends ApiRequest{
    List<Long> userIds;
}
