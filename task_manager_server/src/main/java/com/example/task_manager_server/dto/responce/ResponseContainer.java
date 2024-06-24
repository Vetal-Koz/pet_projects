package com.example.task_manager_server.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseContainer<T> {
    private T data;
}
