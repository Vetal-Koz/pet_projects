package com.example.task_manager_server.entity.data;

import com.example.task_manager_server.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@Entity
@Table(name = "documents")
@NoArgsConstructor
public class Document extends BaseEntity {
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "file_type", nullable = false)
    private String fileType;
    @Lob
    @Column(nullable = false)
    private byte[] data;

    public Document(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    Task task;
}

