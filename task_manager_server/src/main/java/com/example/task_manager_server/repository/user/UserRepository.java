package com.example.task_manager_server.repository.user;

import com.example.task_manager_server.entity.user.User;
import com.example.task_manager_server.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User> {
    @Query(value = "select * from users where id not in(" +
            "select distinct (proj_user.user_id) from proj_user" +
            " where project_id = :projectId)"
            ,nativeQuery = true)
    List<User> findAllByNotInTeamProject(@Param("projectId") Long projectId);

    List<User> findAllByIdIn(List<Long> ids);

    boolean existsByEmail(String email);

}
