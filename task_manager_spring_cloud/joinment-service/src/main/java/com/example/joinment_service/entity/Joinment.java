package com.example.joinment_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ProjUserId.class)
@Table(name = "proj_user")
public class Joinment {
    @Id
    @Column(name = "project_id")
    private Long projectId;
    @Id
    @Column(name = "user_Id")
    private Long userId;
}
