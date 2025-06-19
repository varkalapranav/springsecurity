package com.example.securityapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Users {
    @Id
    private long id;
    private String username;
    private String password;
}
