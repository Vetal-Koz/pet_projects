package com.example.task_manager_server.controller;

import com.example.task_manager_server.dto.response.DocumentResponse;
import com.example.task_manager_server.dto.response.MessageContainer;
import com.example.task_manager_server.dto.response.ResponseContainer;
import com.example.task_manager_server.entity.data.Document;
import com.example.task_manager_server.facade.DocumentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
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
        try {
            Document document = documentFacade.saveAttachment(file, taskId);
            DocumentResponse response = new DocumentResponse(document);
            response.setFileSize(file.getSize());
            return ResponseEntity.ok(new ResponseContainer<>(response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/multiple")
    public ResponseEntity<ResponseContainer<List<DocumentResponse>>> handleMultipleFilesUpload(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("taskId") Long taskId) {
        List<DocumentResponse> responseList = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            try {
                Document document = documentFacade.saveAttachment(file, taskId);
                String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(String.valueOf(document.getId()))
                        .toUriString();
                DocumentResponse response = new DocumentResponse(fileName,
                        downloadUrl,
                        file.getContentType(),
                        file.getSize());
                responseList.add(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.ok(new ResponseContainer<>(responseList));
    }

    @GetMapping("download/{id}")
    public ResponseEntity<ResponseContainer<MessageContainer>> downloadFile(@PathVariable Long id) {
        documentFacade.downloadToLocalStorage(id);
        return ResponseEntity.ok(new ResponseContainer<>(new MessageContainer("Successfully downloaded the file")));
    }
}
