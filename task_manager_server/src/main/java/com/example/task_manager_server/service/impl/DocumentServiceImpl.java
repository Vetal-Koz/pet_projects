package com.example.task_manager_server.service.impl;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Document;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.repository.data.DocumentRepository;
import com.example.task_manager_server.service.DocumentService;
import com.example.task_manager_server.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    @Override
    public void create(Document entity) {
        documentRepository.save(entity);
    }

    @Override
    public void update(Document entity) {
        documentRepository.save(entity);
    }

    @Override
    public Document findById(Long id) {
       return documentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Collection<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Page<Document> findAllByTaskId(Long taskId, DataTableRequest request) {
        Sort sort = Sort.by(
                    request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                    request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return documentRepository.findAllByTaskId(taskId, pageable);
    }
}
