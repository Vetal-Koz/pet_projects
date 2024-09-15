package com.example.joinment_service.repository;

import com.example.joinment_service.entity.Joinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinmentRepository extends JpaRepository<Joinment, Long> {

    List<Long> findByProjectId(Long projectId);

    List<Long> findByUserId(Long userId);
}
