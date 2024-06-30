package com.example.task_manager_server.repository.data;

import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
    @Transactional
    Page<Task> findAllByUserId(Long userId, Pageable pageable);
}
