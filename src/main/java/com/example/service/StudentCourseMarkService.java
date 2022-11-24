package com.example.service;

import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.repository.CourseRepository;
import com.example.repository.StudentCourseMarkRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public StudentCourseMarkDTO toDTO(StudentCourseMarkEntity studentCourseMarkEntity) {
        StudentCourseMarkDTO studentCourseMarkDTO = new StudentCourseMarkDTO();
        studentCourseMarkDTO.setStudent(studentCourseMarkEntity.getStudent());
        studentCourseMarkDTO.setMark(studentCourseMarkEntity.getMark());
        studentCourseMarkDTO.setCourse(studentCourseMarkEntity.getCourse());
        studentCourseMarkDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDTO.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDTO;
    }

    public StudentCourseMarkEntity toEntity(StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkEntity studentCourseMarkEntity = new StudentCourseMarkEntity();

        //studentCourseMarkEntity.setStudent(studentRepository.findById(studentCourseMarkDTO.getStudent().getId()));
        studentCourseMarkEntity.setMark(studentCourseMarkDTO.getMark());
        studentCourseMarkEntity.setCourse(studentCourseMarkDTO.getCourse());
        studentCourseMarkEntity.setId(studentCourseMarkDTO.getId());
        studentCourseMarkEntity.setCreatedDate(studentCourseMarkDTO.getCreatedDate());
        return studentCourseMarkEntity;
    }

    public StudentCourseMarkDTO create(StudentCourseMarkDTO studentCourseMarkDTO) {
        studentCourseMarkRepository.save(toEntity(studentCourseMarkDTO));
        return studentCourseMarkDTO;
    }
}
