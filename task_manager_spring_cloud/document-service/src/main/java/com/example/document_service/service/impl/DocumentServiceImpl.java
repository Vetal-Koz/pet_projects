package com.example.document_service.service.impl;

import com.example.document_service.entity.data.Document;
import com.example.document_service.entity.data.Task;
import com.example.document_service.exception.EntityNotFoundException;
import com.example.document_service.repository.DocumentRepository;
import com.example.document_service.repository.TaskRepository;
import com.example.document_service.service.DocumentService;
import com.example.document_service.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final TaskRepository taskRepository;

    @Override
    public Document create(Document entity) {
        return documentRepository.save(entity);
    }
    @Override
    public Document update(Document entity) {
        return documentRepository.save(entity);
    }
    @Override
    public Document findById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
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
    public Document saveAttachment(MultipartFile file, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        try {
            Document attachment = new Document(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
            attachment.setTask(task);
            return documentRepository.save(attachment);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Document> saveFiles(MultipartFile[] files, Long taskId) {
        List<Document> documents = new ArrayList<>();
        Arrays.asList(files).forEach(file -> {
            try {
                documents.add(saveAttachment(file, taskId));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return documents;
    }
    @Override
    public List<Document> getAllFiles() {
        return documentRepository.findAll();
    }
    @Override
    public void downloadToLocalStorage(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        try (FileOutputStream fos = new FileOutputStream("D:/Folder/" + document.getFileName())) {
            byte[] bytes = document.getData();
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
