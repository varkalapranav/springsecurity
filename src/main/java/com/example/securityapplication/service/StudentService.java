package com.example.securityapplication.service;

import com.example.securityapplication.entity.Student;
import com.example.securityapplication.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;
    public Student register(Student student){
        return studentRepo.save(student);
    }

    public List<Student> findAll(){
        return studentRepo.findAll();
    }
}
