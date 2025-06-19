package com.example.securityapplication.controller;

import com.example.securityapplication.entity.Student;
import com.example.securityapplication.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    StudentService studentService;

//    @PostMapping("/insert")
//    public String insert(HttpServletRequest request){
//        return "hello "+ request.getSession().getId(); //it will give the sessionID
//    }

    @PostMapping("/insert")
    public Student insert(@RequestBody Student student){
      return studentService.register(student);
    }


    @GetMapping("/get-CSRF")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }


    @GetMapping("/getAll")
    public List<Student> getStudent(){
        return studentService.findAll();
    }
}
