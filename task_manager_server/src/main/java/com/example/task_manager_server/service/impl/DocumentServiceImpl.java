package com.example.task_manager_server.service.impl;

import com.example.task_manager_server.dto.request.DataTableRequest;
import com.example.task_manager_server.entity.data.Document;
import com.example.task_manager_server.entity.data.Task;
import com.example.task_manager_server.exception.EntityNotFoundException;
import com.example.task_manager_server.repository.data.DocumentRepository;
import com.example.task_manager_server.repository.data.TaskRepository;
import com.example.task_manager_server.service.DocumentService;
import com.example.task_manager_server.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final TaskRepository taskRepository;

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
    public Page<Document> findAllByTaskId(Long taskId, DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return documentRepository.findAllByTaskId(taskId, pageable);
    }

    @Override
    public Document saveAttachment(MultipartFile file, Long taskId) throws Exception {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Document attachment = new Document(fileName, file.getContentType(), file.getSize(), file.getBytes());
        attachment.setTask(task);
        return documentRepository.save(attachment);
    }

    @Override
    public void saveFiles(MultipartFile[] files, Long taskId) throws Exception {
        Arrays.asList(files).forEach(file -> {
            try {
                saveAttachment(file, taskId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
