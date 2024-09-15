package com.example.task_service.dto.response;

import com.example.task_service.entity.data.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Getter
@Setter
@AllArgsConstructor
public class DocumentResponse extends ApiResponse {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private Long fileSize;

    public DocumentResponse(Document document) {
        setId(document.getId());
        this.fileName = document.getFileName();
        this.fileType = document.getFileType();
        this.downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(document.getId()))
                .toUriString();
        this.fileSize = document.getFileSize();
    }
}
