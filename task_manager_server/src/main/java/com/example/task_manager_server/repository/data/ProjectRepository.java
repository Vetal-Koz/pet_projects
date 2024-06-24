package com.example.task_manager_server.repository.data;

import com.example.task_manager_server.entity.data.Project;
import com.example.task_manager_server.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project> {

    @Query(value = "select * from projects where id in (select Distinct(project_id) from proj_user where user_id = :userId)", nativeQuery = true)
    Page<Project> findAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
