package com.example.task_manager_server.type;


import lombok.Getter;

@Getter
public enum TaskType {
    TO_DO("to do"),
    IN_PROGRESS("in progress"),
    TEST("test"),
    DONE("done");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }
}
