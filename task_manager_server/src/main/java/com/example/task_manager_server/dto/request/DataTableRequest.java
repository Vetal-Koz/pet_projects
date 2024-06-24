package com.example.task_manager_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DataTableRequest {
    private int page;
    private int size;
    private String sort;
    private String order;
}
