package com.example.document_service.service;

import com.example.document_service.entity.BaseEntity;

import java.util.Collection;

public interface CrudService<E extends BaseEntity> {
    E create(E entity);
    E update(E entity);
    E findById(Long id);
    void delete(Long id);

    Collection<E> findAll();
}