package com.example.task_service.dto.request;

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
