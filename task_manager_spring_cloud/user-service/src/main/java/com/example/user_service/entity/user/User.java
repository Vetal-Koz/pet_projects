package com.example.user_service.entity.user;

import com.example.user_service.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;
    private String password;
    private String username;
}
