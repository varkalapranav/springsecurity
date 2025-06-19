package com.example.securityapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private int marks;
}
