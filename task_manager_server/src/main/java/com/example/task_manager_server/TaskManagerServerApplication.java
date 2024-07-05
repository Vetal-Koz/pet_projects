package com.example.task_manager_server;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class TaskManagerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerServerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start(){
//		Task task = new Task();
//		task.setTitle("Task for user 3");
//		task.setDescription("Task 3");
//		task.setAccomplishTo(new Date());
//		task.setUser(null);
//		task.setProject(null);
//		taskService.create(task, 3L, 1L);
	}

}
