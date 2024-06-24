package com.example.task_manager_server.dto.responce;

import com.example.task_manager_server.entity.data.Document;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DocumentResponse extends ApiResponse{
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long fileSize;

    public DocumentResponse(Document document){
        this.fileName = document.getFileName();
        this.fileType = document.getFileType();
    }
}
