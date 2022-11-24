package com.example.controller;

import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.repository.StudentCourseMarkRepository;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;
    @PostMapping("/student/course")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO){
        return ResponseEntity.ok(studentCourseMarkService.create(studentCourseMarkDTO));
    }


}
