package com.example.task_manager_server.repository.data;

import com.example.task_manager_server.entity.data.Document;
import com.example.task_manager_server.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends BaseRepository<Document> {

    Page<Document> findAllByTaskId(Long taskId, Pageable pageable);
}
