package com.example.task_manager_server.controller;

import com.example.task_manager_server.dto.response.DocumentResponse;
import com.example.task_manager_server.dto.response.MessageContainer;
import com.example.task_manager_server.dto.response.ResponseContainer;
import com.example.task_manager_server.facade.DocumentFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class DocumentController {

    private final DocumentFacade documentFacade;

    @GetMapping
    public ResponseEntity<ResponseContainer<List<DocumentResponse>>> findAll() {
        return ResponseEntity.ok(new ResponseContainer<>(documentFacade.getAllFiles()));
    }

    @PostMapping()
    public ResponseEntity<ResponseContainer<DocumentResponse>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("taskId") Long taskId) {
        DocumentResponse response = documentFacade.saveAttachment(file, taskId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseContainer<>(response));
    }

    @PostMapping("/multiple")
    public ResponseEntity<ResponseContainer<List<DocumentResponse>>> handleMultipleFilesUpload(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("taskId") Long taskId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseContainer<>(documentFacade.saveFiles(files, taskId)));
    }

    @GetMapping("download/{id}")
    public ResponseEntity<ResponseContainer<MessageContainer>> downloadFile(@Min(1) @PathVariable Long id) {
        documentFacade.downloadToLocalStorage(id);
        return ResponseEntity.ok(new ResponseContainer<>(new MessageContainer("Successfully downloaded the file")));
    }
}
