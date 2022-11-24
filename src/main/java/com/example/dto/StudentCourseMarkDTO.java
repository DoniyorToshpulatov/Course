package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseMarkDTO {
    private  Integer id;
    private StudentEntity student;
    private CourseEntity course;
    private  Integer mark;
    private LocalDate createdDate;
}
